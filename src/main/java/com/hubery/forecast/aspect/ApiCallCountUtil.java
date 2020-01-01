package com.hubery.forecast.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class ApiCallCountUtil {

  private ConcurrentHashMap<String, AtomicInteger> countMap;

  public ApiCallCountUtil() {
    countMap = new ConcurrentHashMap<>(16);
  }

  public void addCall(String key) {
    countMap.putIfAbsent(key, new AtomicInteger(0));
    countMap.get(key).incrementAndGet();
  }

  public AtomicInteger getCount(String key) {
    return countMap.get(key);
  }
}
