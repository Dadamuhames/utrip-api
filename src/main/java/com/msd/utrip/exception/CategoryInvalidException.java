package com.msd.utrip.exception;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class CategoryInvalidException extends ApplicationException {
    public CategoryInvalidException(ErrorCode error) {
        super(error.getCode(), error.getMessage(), ErrorType.VALIDATION, HttpStatus.BAD_REQUEST);
    }
}
