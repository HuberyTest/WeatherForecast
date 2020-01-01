package com.hubery.forecast.repo;

import com.hubery.forecast.domain.CityInfo;

import java.util.List;

/**
 * Repository for city data. We may need database, txt or other storage.
 */
public interface CityDataRepository {

  List<CityInfo> listCities();

  void addCity(CityInfo cityInfo);

  void updateCity(CityInfo cityInfo);

  void removeCity(String cityId);
}
