package com.hubery.forecast.service;

import com.hubery.forecast.api.ErrorCode;
import com.hubery.forecast.domain.GeneralWeatherReport;
import com.hubery.forecast.domain.enums.ForecastQuerySourceEnum;
import com.hubery.forecast.domain.openweather.WeatherReport;
import com.hubery.forecast.exception.WeatherForecastException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * Implementation for online api provider openweathermap.org
 */
@ConditionalOnProperty(name = "forecast.source", havingValue = "ForecastQueryOpenWeather")
@Component
public class ForecastQueryOpenWeather implements ForecastQuery {

  private RestTemplate restTemplate = new RestTemplate();

  @Value("${openweather.url}")
  private String url;

  @Value("${openweather.appId}")
  private String appId;

  private Properties idMapping = new Properties();

  @PostConstruct
  public void init() throws IOException {
    //Api providers may have different id for each city。So we map them to our own city id in file.
    String mapingFile = "/cities/" + ForecastQuerySourceEnum.openweather.getIdMappingFile();
    ClassPathResource idMappingResource = new ClassPathResource(mapingFile);
    idMapping.load(idMappingResource.getInputStream());
  }

  @Override
  public GeneralWeatherReport getWeatherReport(Integer cityId) {
    //Get the city id provided by the api provider
    Object siteCityId = idMapping.get(cityId.toString());
    if (siteCityId == null) {
      throw new WeatherForecastException(ErrorCode.INVALID_PARAMETER, "Invalid city id");
    } else {
      WeatherReport weatherReport = queryWeatherReport(siteCityId.toString());
      GeneralWeatherReport generalReport = convertToGeneralReport(weatherReport);
      generalReport.setCityId(cityId);
      return generalReport;
    }
  }

  /**
   * Convert to the unified weather report
   * @param weatherReport result of api provider
   * @return
   */
  private GeneralWeatherReport convertToGeneralReport(WeatherReport weatherReport) {
    GeneralWeatherReport report = new GeneralWeatherReport();
    //UTC
    report.setUpdateTime(weatherReport.getDt());

    //kelvin -> celsius
    BigDecimal bd = new BigDecimal(weatherReport.getMain().getTemp() - 273.15);
    bd.setScale(0, BigDecimal.ROUND_HALF_UP);
    report.setTemperature(bd.intValue());

    report.setWeather(weatherReport.getWeather()[0].getDescription());

    report.setWindSpeed(weatherReport.getWind().getSpeed());

    return report;
  }

  /**
   * Query by city id by api provider
   * @param cityId
   * @return
   */
  private WeatherReport queryWeatherReport(String cityId) {
    return restTemplate.getForObject(url, WeatherReport.class, cityId, appId);
  }
}
