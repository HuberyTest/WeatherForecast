package com.hubery.forecast.exception;

import com.hubery.forecast.api.ApiResult;
import com.hubery.forecast.api.ErrorCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(WeatherForecastException.class)
    public ApiResult handleWeatherForecastException(WeatherForecastException ex) {
        return ApiResult.error(ex.getErrorCode(), ex.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ApiResult handleArgumentsErr() {
        return ApiResult.error(ErrorCode.INTERNAL_ERROR, ErrorCode.INTERNAL_ERROR.getDescription());
    }

}
