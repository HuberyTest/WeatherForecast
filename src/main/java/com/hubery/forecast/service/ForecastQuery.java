package com.hubery.forecast.service;

import com.hubery.forecast.domain.GeneralWeatherReport;
import com.hubery.forecast.exception.WeatherForecastException;

/**
 * Provide different api provider by this interface.
 */
public interface ForecastQuery {

  /**
   * Query by our own city id
   * @param cityId this id is allocated by this forecast applications
   * @return report
   */
  GeneralWeatherReport getWeatherReport(Integer cityId) throws WeatherForecastException;
}
