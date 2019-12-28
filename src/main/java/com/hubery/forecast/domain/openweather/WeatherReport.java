package com.hubery.forecast.domain.openweather;

import lombok.Data;

@Data
public class WeatherReport {

  private Long dt;
  private ReportWeather[] weather;
  private ReportTemperature main;
  private ReportWind wind;
}
