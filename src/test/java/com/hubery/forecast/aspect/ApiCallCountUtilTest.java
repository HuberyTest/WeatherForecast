package com.hubery.forecast.aspect;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ApiCallCountUtilTest {

  @Test
  public void test() throws InterruptedException {
    int cnt = 50;
    CountDownLatch cdl = new CountDownLatch(cnt);
    String key = "TEST_CNT_KEY";
    ApiCallCountUtil countUtil = new ApiCallCountUtil();

    for (int i=0; i<cnt; i++) {
      new Thread(() -> {
        countUtil.addCall(key);
        cdl.countDown();
      }).start();
    }

    cdl.await();
    assertEquals(50, countUtil.getCount(key).get());
  }
}