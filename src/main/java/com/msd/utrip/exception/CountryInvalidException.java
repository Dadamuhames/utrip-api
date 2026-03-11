package com.msd.utrip.exception;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class CountryInvalidException extends ApplicationException {
    public CountryInvalidException(ErrorCode error) {
        super(error.getCode(), error.getMessage(), ErrorType.VALIDATION, HttpStatus.BAD_REQUEST);
    }
}
