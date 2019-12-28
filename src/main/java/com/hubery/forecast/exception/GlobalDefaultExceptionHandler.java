package com.hubery.forecast.exception;

import com.hubery.forecast.api.ApiResult;
import com.hubery.forecast.api.ErrorCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(WeatherForecastException.class)
    public ApiResult handleWeatherForecastException(WeatherForecastException ex) {
        return new ApiResult(ex.getErrorCode(), ex.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ApiResult handleArgumentsErr() {
        return new ApiResult(ErrorCode.INTERNAL_ERROR, ErrorCode.INTERNAL_ERROR.getDescription());
    }

}
