package com.hubery.forecast.domain.enums;

import lombok.Getter;

@Getter
public enum ForecastQuerySourceEnum {
  openweather("openWeather_IdMapping.properties");

  private String idMappingFile;

  ForecastQuerySourceEnum(String idMappingFile) {
    this.idMappingFile = idMappingFile;
  }

}
