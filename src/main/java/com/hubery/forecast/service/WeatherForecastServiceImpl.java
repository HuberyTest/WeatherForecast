package com.hubery.forecast.service;

import com.hubery.forecast.domain.CityInfo;
import com.hubery.forecast.repo.CityDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

  @Autowired
  private CityDataRepository cityDataRepository;

  @Override
  public List<CityInfo> listCites() {
    return cityDataRepository.listCities();
  }
}
