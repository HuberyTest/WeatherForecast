package com.hubery.forecast.domain.openweather;

import lombok.Data;

@Data
public class ReportWeather {
  private Integer id;
  private String main;
  private String description;
  private String icon;
}
