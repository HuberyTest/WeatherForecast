package com.hubery.forecast.domain.openweather;

import lombok.Data;

@Data
public class ReportTemperature {
  private Double temp;
  private Double feels_like;
  private Double temp_min;
  private Double temp_max;
  private Integer pressure;
  private Integer humidity;
}
