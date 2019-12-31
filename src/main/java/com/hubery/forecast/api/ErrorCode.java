package com.hubery.forecast.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {

    OK(20000, "OK"),
    INTERNAL_ERROR(20001, "Internal Error"),
    API_QUERY_ERROR(20003, "Open Api fail");

    @Getter
    private final Integer code;
    @Getter
    private final String description;

}