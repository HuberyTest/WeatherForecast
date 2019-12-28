package com.hubery.forecast.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringTestBase {

  @Autowired
  protected TestRestTemplate testRestTemplate;

  /**
   * query Genericity apiResult
   */
  public <T> T getApiResult(String url, ParameterizedTypeReference<T> resultType, Object... urlVariables) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity reqEntity = new HttpEntity(headers, null);
    ResponseEntity<T> result = testRestTemplate.exchange(url, HttpMethod.GET, reqEntity, resultType, 1);
    return result.getBody();
  }
}
