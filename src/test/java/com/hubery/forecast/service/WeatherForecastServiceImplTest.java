package com.hubery.forecast.service;

import com.hubery.forecast.base.SpringTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.security.InvalidParameterException;

class WeatherForecastServiceImplTest extends SpringTestBase {

  @Resource(name = "weatherForecastServiceImpl")
  private WeatherForecastService forecastService;

  @Test
  public void testException() {
    Assertions.assertThrows(InvalidParameterException.class, () -> forecastService.queryWeatherReport("-1"));
  }
}