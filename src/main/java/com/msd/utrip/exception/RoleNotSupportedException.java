package com.msd.utrip.exception;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class RoleNotSupportedException extends ApplicationException {
  public RoleNotSupportedException(ErrorCode error) {
    super(error.getCode(), error.getMessage(), ErrorType.EXTERNAL, HttpStatus.BAD_REQUEST);
  }
}
