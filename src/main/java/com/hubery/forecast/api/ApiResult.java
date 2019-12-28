package com.hubery.forecast.api;

import lombok.Getter;

@Getter
public class ApiResult<T> {
  private Integer code;
  private T data;
  private String message;

  /**
   * for serialization
   */
  public ApiResult() {}

  public ApiResult(T data) {
    this.code = ErrorCode.OK.getCode();
    this.data = data;
  }

  public ApiResult(ErrorCode errorCode, T data) {
    this.code = errorCode.getCode();
    this.message = errorCode.getDescription();
    this.data = data;
  }

}
