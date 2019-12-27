package com.hubery.forecast.domain;

import lombok.Data;

@Data
public class CityInfo {
  private Integer id;

  private String name;

  private boolean enabled;
}
