package com.hubery.forecast.domain.openWeather;

import lombok.Data;

@Data
public class ReportWeather {
  private Integer id;
  private String main;
  private String description;
  private String icon;
}
