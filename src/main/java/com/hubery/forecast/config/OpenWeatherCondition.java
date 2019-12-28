package com.hubery.forecast.config;


import com.hubery.forecast.domain.enums.ForecastQuerySourceEnum;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * If parameter cityinfo.storage=text, create CityDataTextRepository
 */
public class OpenWeatherCondition implements Condition {

  private static final String QUERY_SOURCE_KEY = "forecast.source";

  @Override
  public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
    return conditionContext.getEnvironment().getProperty(QUERY_SOURCE_KEY).equals(ForecastQuerySourceEnum.openweather.name());
  }
}
