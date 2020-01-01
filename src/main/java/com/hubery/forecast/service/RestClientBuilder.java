package com.hubery.forecast.service;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestClientBuilder {
  public static RestTemplate buildRestClient() {
    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    requestFactory.setReadTimeout(5000);
    requestFactory.setConnectTimeout(10000);
    RestTemplate rt = new RestTemplate(requestFactory);
    return rt;
  }
}
