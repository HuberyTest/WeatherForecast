package com.hubery.forecast.service;

import com.hubery.forecast.domain.CityInfo;
import com.hubery.forecast.domain.GeneralWeatherReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.security.InvalidParameterException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class WeatherForecastServiceImpl implements WeatherForecastService {

  @Resource(name = "weatherForecastProviderCaller")
  private WeatherForecastService weatherForecastService;

  private Map<String, CityInfo> cityMap = new ConcurrentHashMap<>(16);

  @Autowired
  private CityDataService cityDataService;

  @PostConstruct
  public void init() {
    for (CityInfo cityInfo : cityDataService.listCities()) {
      cityMap.put(cityInfo.getId(), cityInfo);
    }
  }

  @Override
  @Cacheable(value = "forecastReport")
  public GeneralWeatherReport queryWeatherReport(String cityId) {
    CityInfo cityInfo = cityMap.get(cityId);
    if (cityInfo == null) {
      throw new InvalidParameterException("Invalid city id " + cityId);
    }
    synchronized (cityInfo) {
      return weatherForecastService.queryWeatherReport(cityId);
    }
  }
}
