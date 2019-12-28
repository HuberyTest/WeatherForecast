package com.hubery.forecast.exception;

import com.hubery.forecast.api.ErrorCode;
import lombok.Getter;

@Getter
public class WeatherForecastException extends Exception {
  private ErrorCode errorCode;

  private String msg;

  public WeatherForecastException(ErrorCode errorCode, String msg) {
    super(msg);
    this.msg = msg;
    this.errorCode = errorCode;
  }
}
