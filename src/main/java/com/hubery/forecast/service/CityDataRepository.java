package com.hubery.forecast.service;

import com.hubery.forecast.domain.CityInfo;

import java.util.List;

public interface CityDataRepository {

  List<CityInfo> listCities();

  void addCity(CityInfo cityInfo);

  void removeCity(Integer cityId);
}
