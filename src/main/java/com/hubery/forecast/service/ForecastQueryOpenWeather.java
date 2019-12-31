package com.hubery.forecast.service;

import com.hubery.forecast.api.ErrorCode;
import com.hubery.forecast.domain.GeneralWeatherReport;
import com.hubery.forecast.domain.openweather.WeatherReport;
import com.hubery.forecast.exception.WeatherForecastException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * Implementation for online api provider openweathermap.org
 */
@Component("openweather")
@Slf4j
public class ForecastQueryOpenWeather implements ForecastQuery {

  private RestTemplate restTemplate = new RestTemplate();

  @Value("${openweather.url}")
  private String url;

  @Value("${openweather.appId}")
  private String appId;

  @Override
  @Cacheable(value = "forecastReport")
  public GeneralWeatherReport getWeatherReport(String siteCityId) throws WeatherForecastException {
    WeatherReport weatherReport = queryWeatherReport(siteCityId);
    GeneralWeatherReport generalReport = convertToGeneralReport(weatherReport);
    generalReport.setCityId(siteCityId);
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
   * @param cityId id by api provider
   * @return Report by api provider
   */
  private WeatherReport queryWeatherReport(String cityId) throws WeatherForecastException {
    try {
      log.info("query open Api for city {}", cityId);
      return restTemplate.getForObject(url, WeatherReport.class, cityId, appId);
    } catch (Exception e) {
      log.info("query open weather fail {} {}", cityId, e.getMessage());
      e.printStackTrace();
      throw new WeatherForecastException(ErrorCode.API_QUERY_ERROR, e.getMessage());
    }
  }
}
