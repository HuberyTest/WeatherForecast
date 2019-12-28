package com.hubery.forecast.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.hubery.forecast.domain.GeneralWeatherReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WeatherForecastServiceImpl implements WeatherForecastService {

  @Autowired
  private WeatherForecastQuery weatherForecastQuery;

  private LoadingCache<Integer, GeneralWeatherReport> reportCache;

  @PostConstruct
  public void init() {
    //For performance and service restriction
    reportCache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<Integer, GeneralWeatherReport>() {
                      @Override
                      public GeneralWeatherReport load(Integer cityId) {
                        try {
                          return weatherForecastQuery.getWeatherReport(cityId);
                        } catch (Exception e) {
                          //cache empty report in case of any error
                          log.error("query weather report {} error {}", cityId, e.getMessage());
                          e.printStackTrace();
                          return new GeneralWeatherReport();
                        }
                      }
                    });
  }

  @Override
  public GeneralWeatherReport queryWeatherReport(Integer cityId) {
    try {
      return reportCache.get(cityId);
    } catch (ExecutionException e) {
      log.error("query weather report fail {} {}", cityId, e.getMessage());
      e.printStackTrace();
      return new GeneralWeatherReport();
    }
  }
}
