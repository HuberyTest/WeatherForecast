package com.hubery.forecast.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResult {
  private Integer code;
  private Object data;
  private String message;

  public ApiResult() {
  }

  public ApiResult(ErrorCode errorCode, String message, Object data) {
    this.code = errorCode.getCode();
    this.message = message;
    this.data = data;
  }

  public ApiResult(ErrorCode errorCode, Object data) {
    this.code = errorCode.getCode();
    this.message = errorCode.getDescription();
    this.data = data;
  }

  public static ApiResult success() {
    return success(null);
  }

  public static ApiResult success(Object data) {
    return new ApiResult(ErrorCode.OK, data);
  }

  public static ApiResult error(ErrorCode errorCode, String message) {
    return new ApiResult(errorCode, message, null);
  }

}
