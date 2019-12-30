package com.hubery.forecast.service;

import com.hubery.forecast.domain.GeneralWeatherReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WeatherForecastServiceImpl implements WeatherForecastService {

  @Autowired
  private ApplicationContext applicationContext;

  @Value("${forecast.provider}")
  private String forecastProviderNamee;

  private ForecastQuery getForecastProvider() {
    //in case to switch the provider at runtime
    return applicationContext.getBean(forecastProviderNamee, ForecastQuery.class);
  }

  @Override
  @Cacheable(value = "forecastReport")
  public GeneralWeatherReport queryWeatherReport(Integer cityId) {
    return getForecastProvider().getWeatherReport(cityId);
  }
}
