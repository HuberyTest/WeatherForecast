package com.hubery.forecast.controller;

import com.hubery.forecast.api.ApiResult;
import com.hubery.forecast.domain.CityInfo;
import com.hubery.forecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/forecast")
public class WeatherForecastController {

  @Autowired
  private WeatherForecastService weatherForecastService;

  @GetMapping("/cities")
  public ApiResult listCities() {
    List<CityInfo> cityInfos = weatherForecastService.listCites();
    return ApiResult.success(cityInfos);
  }
}
