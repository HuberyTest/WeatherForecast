package com.hubery.forecast.service;

import com.hubery.forecast.domain.GeneralWeatherReport;
import com.hubery.forecast.domain.enums.ForecastQuerySourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
@Slf4j
public class WeatherForecastServiceImpl implements WeatherForecastService {

  private ApplicationContext applicationContext;

  private Map<String, String> idMapping;

  @PostConstruct
  public void init() throws IOException {
    //Api providers may have different id for each city。So we map them to our own city id in file.
    String mapingFile = "/cities/" + ForecastQuerySourceEnum.openweather.getIdMappingFile();
    ClassPathResource idMappingResource = new ClassPathResource(mapingFile);
    Properties properties = new Properties();
    properties.load(idMappingResource.getInputStream());

    idMapping = new HashMap<>();
    for (String key : properties.stringPropertyNames()) {
      idMapping.put(key, properties.get(key).toString());
    }
  }

  @Autowired
  public WeatherForecastServiceImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Value("${forecast.provider}")
  private String forecastProviderNamee;

  private ForecastQuery getForecastProvider() {
    //In case to switch the provider at runtime
    return applicationContext.getBean(forecastProviderNamee, ForecastQuery.class);
  }

  @Override
  @Cacheable(value = "forecastReport")
  public GeneralWeatherReport queryWeatherReport(String cityId) {
    try {
      //Get the city id provided by the api provider
      String siteCityId = idMapping.get(cityId);
      if (siteCityId == null) {
        throw new InvalidParameterException("Invalid city id");
      }
      synchronized (siteCityId) {
        //The below method is also annotated with @Cacheable
        return getForecastProvider().getWeatherReport(siteCityId);
      }
    } catch (Exception e) {
      //Cache the empty report for any error, query after 5 minutes if any error
      return new GeneralWeatherReport();
    }
  }
}
