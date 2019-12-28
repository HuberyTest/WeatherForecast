package com.hubery.forecast.domain;

import lombok.Data;

@Data
public class GeneralWeatherReport {
  private Integer cityId;

  private Long updateTime;

  private String weather;

  private Integer temperature;

  private Double windSpeed;
}
