package com.hubery.forecast.controller;

import com.hubery.forecast.api.ApiResult;
import com.hubery.forecast.domain.CityInfo;
import com.hubery.forecast.domain.GeneralWeatherReport;
import com.hubery.forecast.service.CityDataService;
import com.hubery.forecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/forecast")
public class WeatherForecastController {

  @Resource(name = "weatherForecastServiceImpl")
  private WeatherForecastService weatherForecastService;

  private CityDataService cityDataService;

  @Autowired
  public WeatherForecastController(CityDataService cityDataService) {
    this.cityDataService = cityDataService;
  }

  @GetMapping("/cities")
  public ApiResult<List<CityInfo>> listCities() {
    List<CityInfo> cityInfos = cityDataService.listCities();
    return new ApiResult<>(cityInfos);
  }

  @GetMapping("/city/{cityId}")
  public ApiResult<GeneralWeatherReport> queryCityWeatherReport(@PathVariable("cityId") String cityId) {
    GeneralWeatherReport report = weatherForecastService.queryWeatherReport(cityId);
    return new ApiResult<>(report);
  }
}
