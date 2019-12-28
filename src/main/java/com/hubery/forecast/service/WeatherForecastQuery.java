package com.hubery.forecast.service;

import com.hubery.forecast.domain.GeneralWeatherReport;

/**
 * Provide different data source by this interface.
 */
public interface WeatherForecastQuery {
  GeneralWeatherReport getWeatherReport(Integer cityId);
}
