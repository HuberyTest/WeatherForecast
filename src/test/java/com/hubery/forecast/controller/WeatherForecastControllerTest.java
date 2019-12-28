package com.hubery.forecast.controller;

import com.hubery.forecast.api.ApiResult;
import com.hubery.forecast.api.ErrorCode;
import com.hubery.forecast.domain.CityInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class WeatherForecastControllerTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  void listCities() {
    ApiResult result = testRestTemplate.getForObject("/v1/forecast/cities", ApiResult.class);
    assertThat(result.getCode()).isEqualTo(ErrorCode.OK.getCode());
    assertThat(((List<CityInfo>)result.getData()).size()).isEqualTo(3);

  }
}