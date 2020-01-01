package com.hubery.forecast.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IdMapping {
  private String innerId;
  private String providerId;
}
