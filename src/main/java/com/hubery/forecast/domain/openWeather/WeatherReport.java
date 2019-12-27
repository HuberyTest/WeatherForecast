package com.hubery.forecast.domain.openWeather;

import lombok.Data;

@Data
public class WeatherReport {

  private Long dt;
  private ReportWeather[] weather;
  private ReportTemperature main;
  private ReportWind wind;
}
