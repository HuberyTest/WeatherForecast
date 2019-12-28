package com.hubery.forecast.repo;

import com.hubery.forecast.domain.CityInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CityDataTextRepositoryTest {

  @Test
  void listCities() {
    CityDataTextRepository textRepository = new CityDataTextRepository();
    textRepository.init();
    List<CityInfo> cityInfoList = textRepository.listCities();
    assertThat(cityInfoList.size()).isEqualTo(3);
  }

  @Test
  void addCity() {
    CityDataTextRepository textRepository = new CityDataTextRepository();
    textRepository.init();
    textRepository.addCity(new CityInfo(4, "test city"));
    List<CityInfo> cityInfoList = textRepository.listCities();
    assertThat(cityInfoList.size()).isEqualTo(4);
  }

  @Test
  void updateCity() {
    CityDataTextRepository textRepository = new CityDataTextRepository();
    textRepository.init();
    textRepository.updateCity(new CityInfo(1, "changedName"));
    List<CityInfo> cityInfoList = textRepository.listCities();
    long cnt = cityInfoList.stream().filter(r -> r.getName().equals("changedName")).count();
    assertThat(cnt).isEqualTo(1);
  }

  @Test
  void removeCity() {
    CityDataTextRepository textRepository = new CityDataTextRepository();
    textRepository.init();
    textRepository.removeCity(1);
    List<CityInfo> cityInfoList = textRepository.listCities();
    assertThat(cityInfoList.size()).isEqualTo(2); //, "Two cities left");
  }
}