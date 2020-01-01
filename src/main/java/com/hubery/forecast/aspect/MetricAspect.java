package com.hubery.forecast.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MetricAspect {

  @Autowired
  private ApiCallCountUtil callCountUtil;

  @Around("execution(* com.hubery.forecast.service.ForecastQuery.getWeatherReport(..))")
  public Object afterOpenApiCalled(ProceedingJoinPoint pjp) throws Throwable {
    callCountUtil.addCall(pjp.getTarget().getClass().getSimpleName() + ".getWeatherReport");
    return pjp.proceed();
  }
}
