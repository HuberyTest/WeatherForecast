package com.hubery.forecast.service;

import com.hubery.forecast.domain.CityInfo;

import java.util.List;

/**
 * City data CURD service
 */
public interface CityDataService {
  List<CityInfo> listCities();

  void addCity(CityInfo cityInfo);

  void updateCity(CityInfo cityInfo);

  void removeCity(String cityId);
}
