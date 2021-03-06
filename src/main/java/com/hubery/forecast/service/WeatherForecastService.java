package com.hubery.forecast.service;

import com.hubery.forecast.domain.GeneralWeatherReport;


/**
 * Query weather forecast report service
 */
public interface WeatherForecastService {
  /**
   * query the weather forecast report by cityId
   * @param cityId inner city id
   * @return report
   */
  GeneralWeatherReport queryWeatherReport(String cityId);

}
