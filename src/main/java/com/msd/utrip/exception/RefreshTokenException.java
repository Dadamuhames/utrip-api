package com.msd.utrip.exception;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class RefreshTokenException extends ApplicationException {

  public RefreshTokenException(int code, String message, ErrorType errorType, HttpStatus status) {
    super(code, message, errorType, status);
  }

  public RefreshTokenException(ErrorCode error) {
    super(error.getCode(), error.getMessage(), ErrorType.VALIDATION, HttpStatus.BAD_REQUEST);
  }
}
