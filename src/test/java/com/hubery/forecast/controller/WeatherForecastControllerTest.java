package com.hubery.forecast.controller;

import com.hubery.forecast.api.ApiResult;
import com.hubery.forecast.api.ErrorCode;
import com.hubery.forecast.base.SpringTestBase;
import com.hubery.forecast.domain.CityInfo;
import com.hubery.forecast.domain.GeneralWeatherReport;
import com.hubery.forecast.repo.CityDataRepository;
import com.hubery.forecast.service.WeatherForecastQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class WeatherForecastControllerTest extends SpringTestBase {


  @MockBean
  private WeatherForecastQuery weatherForecastQuery;

  @Test
  public void listCities() {
    ApiResult<List<CityInfo>> result = getApiResult("/v1/forecast/cities", new ParameterizedTypeReference<ApiResult<List<CityInfo>>>() {});
    assertThat(result.getCode()).isEqualTo(ErrorCode.OK.getCode());
    assertThat(result.getData().size()).isEqualTo(3);
  }

  @Test
  public void queryCityWeather() {
    String mockWeather = "scattered clouds";
    GeneralWeatherReport mockReport = new GeneralWeatherReport();
    mockReport.setWeather(mockWeather);
    when(weatherForecastQuery.getWeatherReport(anyInt())).thenReturn(mockReport);

    ApiResult<GeneralWeatherReport> result = getApiResult("/v1/forecast/city/{cityId}", new ParameterizedTypeReference<ApiResult<GeneralWeatherReport>>() {}, 1);
    assertThat(result.getCode()).isEqualTo(ErrorCode.OK.getCode());
    assertThat(result.getData().getWeather()).isEqualTo(mockWeather);
  }
}