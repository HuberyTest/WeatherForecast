package com.hubery.forecast.service;

import com.hubery.forecast.domain.CityInfo;

import java.util.List;

/**
 * City data CURD service
 */
public interface CityDataService {
  List<CityInfo> listCities();

}
