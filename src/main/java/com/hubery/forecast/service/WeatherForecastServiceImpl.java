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

  private ApplicationContext applicationContext;

  @Autowired
  public WeatherForecastServiceImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Value("${forecast.provider}")
  private String forecastProviderNamee;

  private ForecastQuery getForecastProvider() {
    //In case to switch the provider at runtime
    return applicationContext.getBean(forecastProviderNamee, ForecastQuery.class);
  }

  @Override
  @Cacheable(value = "forecastReport")
  public GeneralWeatherReport queryWeatherReport(Integer cityId) {
    try {
      return getForecastProvider().getWeatherReport(cityId);
    } catch (Exception e) {
      //Cache the empty report for any error, query after 5 minutes if any error
      return new GeneralWeatherReport();
    }
  }
}
