package com.hubery.forecast.service;

import com.hubery.forecast.domain.CityInfo;

import java.util.List;

/**
 *
 */
public interface WeatherForecastService {
  List<CityInfo> listCites();
}
