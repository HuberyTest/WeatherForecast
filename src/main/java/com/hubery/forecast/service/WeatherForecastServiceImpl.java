package com.hubery.forecast.service;

import com.hubery.forecast.domain.GeneralWeatherReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Cacheable()
public class WeatherForecastServiceImpl implements WeatherForecastService {

  @Autowired
  private ForecastQuery forecastQuery;

  @Override
  @Cacheable(value = "forecastReport")
  public GeneralWeatherReport queryWeatherReport(Integer cityId) {
    return forecastQuery.getWeatherReport(cityId);
  }
}
