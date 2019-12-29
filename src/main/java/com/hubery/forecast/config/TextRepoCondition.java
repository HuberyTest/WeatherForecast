package com.hubery.forecast.config;


import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * If parameter cityinfo.storage=text, create CityDataTextRepository
 */
public class TextRepoCondition extends SpringBootCondition {

  private static final String STORAGE_KEY = "cityinfo.storage";
  private static final String STORAGE_TEXT = "text";

  @Override
  public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
    if (context.getEnvironment().getProperty(STORAGE_KEY).equals(STORAGE_TEXT)) {
      return ConditionOutcome.match("Use text file to storage city infomation");
    } else {
      return ConditionOutcome.noMatch("Not use text file to storage city infomation");
    }
  }

}
