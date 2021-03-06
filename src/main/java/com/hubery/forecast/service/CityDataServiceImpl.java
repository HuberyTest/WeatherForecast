package com.hubery.forecast.service;

import com.hubery.forecast.domain.CityInfo;
import com.hubery.forecast.repo.CityDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {

  @Autowired
  private CityDataRepository cityDataRepository;

  @Override
  @Cacheable("cities")
  public List<CityInfo> listCities() {
    return cityDataRepository.listCities();
  }

  @Override
  public void addCity(CityInfo cityInfo) {
    cityDataRepository.addCity(cityInfo);
  }

  @Override
  public void updateCity(CityInfo cityInfo) {
    cityDataRepository.updateCity(cityInfo);
  }

  @Override
  public void removeCity(String cityId) {
    cityDataRepository.removeCity(cityId);
  }
}
