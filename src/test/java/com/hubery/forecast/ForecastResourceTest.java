package com.hubery.forecast;

import com.hubery.forecast.domain.openweather.WeatherReport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Disabled
public class ForecastResourceTest {

  @Test
  public void testOpenWeather() {
    RestTemplate rt = new RestTemplate();
    String appId = "62976abd493350075f9d1be43cbfaaf9";
    Integer cityId = 2158177;  //Melbourne
    //2147714 Sydney
    //2171507 Wollongong
//    String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=62976abd493350075f9d1be43cbfaaf9";
    String url = "http://api.openweathermap.org/data/2.5/weather?id={id}&APPID={appId}";
    WeatherReport result = rt.getForObject(url, WeatherReport.class, cityId, appId);
    if (result != null) {
      log.info(result.getWeather()[0].getDescription());
    } else {
      log.info("api not available");
    }
  }


}
