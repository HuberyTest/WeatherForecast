package com.hubery.forecast.config;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Check if parameter cityinfo.storage=text, create CityDataTextRepository
 */
public class TextRepoCondition implements Condition {

  private static final String STORAGE_KEY = "cityinfo.storage";
  private static final String STORAGE_TEXT = "text";

  @Override
  public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
    return conditionContext.getEnvironment().getProperty(STORAGE_KEY).equals(STORAGE_TEXT);
  }
}
