package com.hubery.forecast.controller;

import com.hubery.forecast.aspect.ApiCallCountUtil;
import com.hubery.forecast.base.SpringTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCacheIntegrationTest extends SpringTestBase {

  @Autowired
  private WeatherForecastController weatherForecastController;

  @Autowired
  private ApiCallCountUtil apiCallCountUtil;

  @Test
  public void testPerformance() throws InterruptedException {
    //Open Api should be queried for once
    int cnt = 10 ;
    CountDownLatch cdl = new CountDownLatch(cnt);
    for (int i=0; i<cnt; i++) {
      new Thread(() -> {
        weatherForecastController.queryCityWeatherReport("1");
        cdl.countDown();
      }).start();
    }
    cdl.await();

    int cntCalled = apiCallCountUtil.getCount("ForecastQueryOpenWeather.getWeatherReport").intValue();
    assertThat(cntCalled).isEqualTo(1);
  }
}
