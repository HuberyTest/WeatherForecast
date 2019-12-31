package com.hubery.forecast.service;

import com.hubery.forecast.exception.WeatherForecastException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class ForecastQueryOpenWeatherWeatherTest {

  @Test
  void getWeatherReport() throws IOException {
    ForecastQueryOpenWeather openWeather = new ForecastQueryOpenWeather();
    openWeather.init();
    assertThrows(InvalidParameterException.class, () -> openWeather.getWeatherReport(-1));
  }
}