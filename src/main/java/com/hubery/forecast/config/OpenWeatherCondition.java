package com.hubery.forecast.config;


import com.hubery.forecast.domain.enums.ForecastQuerySourceEnum;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * If parameter cityinfo.storage=text, create CityDataTextRepository
 */
public class OpenWeatherCondition extends SpringBootCondition {

  private static final String QUERY_SOURCE_KEY = "forecast.source";

  @Override
  public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
    if (context.getEnvironment().getProperty(QUERY_SOURCE_KEY).equals(ForecastQuerySourceEnum.openweather.name())) {
      return ConditionOutcome.match("Use openweathermap.org");
    } else {
      return ConditionOutcome.noMatch("Dont use openweathermap.org");
    }
  }
}
