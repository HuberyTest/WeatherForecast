package com.hubery.forecast.service;

import com.hubery.forecast.exception.WeatherForecastException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class QuerySourceOpenWeatherTest {

  @Test
  void getWeatherReport() throws IOException {
    QuerySourceOpenWeather openWeather = new QuerySourceOpenWeather();
    openWeather.init();
    assertThrows(WeatherForecastException.class, () -> openWeather.getWeatherReport(-1));
  }
}