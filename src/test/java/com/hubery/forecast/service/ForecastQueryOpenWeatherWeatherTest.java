package com.hubery.forecast.service;

import com.hubery.forecast.domain.GeneralWeatherReport;
import com.hubery.forecast.domain.openweather.ReportTemperature;
import com.hubery.forecast.domain.openweather.ReportWeather;
import com.hubery.forecast.domain.openweather.ReportWind;
import com.hubery.forecast.domain.openweather.WeatherReport;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class ForecastQueryOpenWeatherWeatherTest {

  @Test
  void getWeatherReport() {
    ForecastQueryOpenWeather openWeather = new ForecastQueryOpenWeather();
    WeatherReport report = new WeatherReport();
    Long dt = new Date().getTime();
    report.setDt(dt);
    ReportTemperature temperature = new ReportTemperature();
    temperature.setTemp(5.5 + 273.15);
    report.setMain(temperature);

    ReportWeather[] weather = new ReportWeather[1];
    ReportWeather rw = new ReportWeather();
    rw.setDescription("cloud");
    weather[0] = rw;
    report.setWeather(weather);

    ReportWind wind = new ReportWind();
    wind.setSpeed(2.1);
    report.setWind(wind);

    GeneralWeatherReport generalWeatherReport = openWeather.convertToGeneralReport(report);
    assertThat(generalWeatherReport.getTemperature()).isEqualTo(6);
    assertThat(generalWeatherReport.getWeather()).isEqualTo("cloud");
    assertThat(generalWeatherReport.getUpdateTime()).isEqualTo(dt);
    assertThat(generalWeatherReport.getWindSpeed()).isEqualTo(2.1);
  }
}