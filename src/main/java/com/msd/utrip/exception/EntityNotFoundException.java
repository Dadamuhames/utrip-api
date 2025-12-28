package com.msd.utrip.exception;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApplicationException {

  public EntityNotFoundException() {
    super(
        ErrorCode.ENTITY_NOT_FOUND_CODE.getCode(),
        ErrorCode.ENTITY_NOT_FOUND_CODE.getMessage(),
        ErrorType.VALIDATION,
        HttpStatus.NOT_FOUND);
  }
}
