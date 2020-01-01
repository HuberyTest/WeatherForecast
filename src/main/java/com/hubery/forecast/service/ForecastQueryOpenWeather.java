package com.hubery.forecast.service;

import com.hubery.forecast.api.ErrorCode;
import com.hubery.forecast.domain.GeneralWeatherReport;
import com.hubery.forecast.domain.IdMapping;
import com.hubery.forecast.domain.enums.ForecastQuerySourceEnum;
import com.hubery.forecast.domain.openweather.WeatherReport;
import com.hubery.forecast.exception.WeatherForecastException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Implementation for online api provider openweathermap.org
 */
@Component("openweather")
@Slf4j
public class ForecastQueryOpenWeather implements ForecastQuery {

  private RestTemplate restTemplate;

  private Map<String, IdMapping> idMappings;

  @Value("${openweather.url}")
  private String url;

  @Value("${openweather.appId}")
  private String appId;

  @PostConstruct
  public void init() throws IOException {
    //Api providers may have different id for each city。So we map them to our own city id in file.
    idMappings = loadIdMapping();

    restTemplate = RestClientBuilder.buildRestClient();
  }

  private HashMap<String, IdMapping> loadIdMapping() throws IOException {
    String mapingFile = "/cities/" + ForecastQuerySourceEnum.openweather.getIdMappingFile();
    ClassPathResource idMappingResource = new ClassPathResource(mapingFile);
    Properties properties = new Properties();
    properties.load(idMappingResource.getInputStream());

    HashMap<String, IdMapping> idMappings = new HashMap<>();
    for (String key : properties.stringPropertyNames()) {
      idMappings.put(key, new IdMapping(key, properties.get(key).toString()));
    }
    return idMappings;
  }

  @Override
  public GeneralWeatherReport getWeatherReport(String cityId) throws WeatherForecastException {
    IdMapping idMapping = idMappings.get(cityId);
    if (idMapping == null) {
      throw new InvalidParameterException("Invalid city id");
    }
    String providerCityId = idMapping.getProviderId();
    WeatherReport weatherReport = queryWeatherReport(providerCityId);
    GeneralWeatherReport generalReport = convertToGeneralReport(weatherReport);
    generalReport.setCityId(providerCityId);
    return generalReport;
  }

  /**
   * Convert to the unified weather report
   * @param weatherReport result of api provider
   * @return GeneralWeatherReport
   */
  public GeneralWeatherReport convertToGeneralReport(WeatherReport weatherReport) {
    GeneralWeatherReport report = new GeneralWeatherReport();
    //UTC
    report.setUpdateTime(weatherReport.getDt());

    //kelvin -> celsius
    BigDecimal bd = new BigDecimal(weatherReport.getMain().getTemp() - 273.15);
    bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
    report.setTemperature(bd.intValue());

    report.setWeather(weatherReport.getWeather()[0].getDescription());

    report.setWindSpeed(weatherReport.getWind().getSpeed());

    return report;
  }

  /**
   * Query by city id by api provider
   * @param providerCityId id by api provider
   * @return Report by api provider
   */
  private WeatherReport queryWeatherReport(String providerCityId) throws WeatherForecastException {
    try {
      log.info("query open Api for city {}", providerCityId);
      return restTemplate.getForObject(url, WeatherReport.class, providerCityId, appId);
    } catch (Exception e) {
      log.info("query open weather fail {} {}", providerCityId, e.getMessage());
      e.printStackTrace();
      throw new WeatherForecastException(ErrorCode.API_QUERY_ERROR, e.getMessage());
    }
  }
}
