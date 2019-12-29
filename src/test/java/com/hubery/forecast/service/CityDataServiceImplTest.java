package com.hubery.forecast.service;

import com.hubery.forecast.base.SpringTestBase;
import com.hubery.forecast.repo.CityDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CityDataServiceImplTest extends SpringTestBase {

  @SpyBean
  CityDataRepository cityDataRepository;

  @Autowired
  CityDataService cityDataService;

  @Test
  void listCities() {
    assertThat(cityDataService.listCities().size()).isEqualTo(3);
    when(cityDataRepository.listCities()).thenReturn(null);
    assertThat(cityDataService.listCities()).isNotNull();
  }
}