package com.hubery.forecast.repo;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubery.forecast.api.ErrorCode;
import com.hubery.forecast.domain.CityInfo;
import com.hubery.forecast.exception.WeatherForecastException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Use txt file to store city data
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "cityinfo.storage", havingValue = "text")
public class CityDataTextRepository implements CityDataRepository {

  private List<CityInfo> cityInfos = new ArrayList<>();

  @PostConstruct
  public void init() throws IOException {
    ClassPathResource cityResource = new ClassPathResource("/cities/cities.json");
    ObjectMapper mapper = new ObjectMapper();
    JavaType cityInfoType = mapper.getTypeFactory().constructParametricType(List.class, CityInfo.class);
    cityInfos = mapper.readValue(cityResource.getInputStream(), cityInfoType);
  }

  @Override
  public List<CityInfo> listCities() {
    return cityInfos;
  }

  private CityInfo foundById(Integer cityId) {
    for (CityInfo cityInfo : cityInfos) {
      if (cityInfo.getId().equals(cityId)) {
        return cityInfo;
      }
    }
    return null;
  }

  @Override
  public synchronized void addCity(CityInfo cityInfo) {
    CityInfo found = foundById(cityInfo.getId());
    if (found != null) {
      found.setName(cityInfo.getName());
    } else {
      cityInfos.add(cityInfo);
    }
  }

  @Override
  public synchronized void updateCity(CityInfo cityInfo) {
    CityInfo found = foundById(cityInfo.getId());
    if (found != null) {
      found.setName(cityInfo.getName());
    } else {
      throw new WeatherForecastException(ErrorCode.INVALID_PARAMETER, "City not exists");
    }
  }

  @Override
  public synchronized void removeCity(Integer cityId) {
    CityInfo found = foundById(cityId);
    if (found != null) {
      cityInfos.remove(found);
    } else {
      throw new WeatherForecastException(ErrorCode.INVALID_PARAMETER, "City not exists");
    }
  }
}
