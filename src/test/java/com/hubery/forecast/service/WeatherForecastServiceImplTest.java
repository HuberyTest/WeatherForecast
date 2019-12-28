package com.hubery.forecast.service;

import com.hubery.forecast.base.SpringTestBase;
import com.hubery.forecast.domain.GeneralWeatherReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class WeatherForecastServiceImplTest extends SpringTestBase {

  @MockBean
  private WeatherForecastQuery weatherForecastQuery;

  @Autowired
  private WeatherForecastService forecastService;

  @Test
  void testCache() {
    when(weatherForecastQuery.getWeatherReport(anyInt())).thenReturn(new GeneralWeatherReport());
    GeneralWeatherReport report = forecastService.queryWeatherReport(1);
    assertThat(report).isNotNull();

    when(weatherForecastQuery.getWeatherReport(anyInt())).thenReturn(null);
    report = forecastService.queryWeatherReport(1);
    assertThat(report).isNotNull();
  }
}