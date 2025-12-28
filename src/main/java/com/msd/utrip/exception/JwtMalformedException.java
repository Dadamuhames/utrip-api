package com.msd.utrip.exception;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.ErrorType;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;

public class JwtMalformedException extends ApplicationException {

  public JwtMalformedException(ErrorCode error, JwtException ex) {
    super(error.getCode(), ex.getMessage(), ErrorType.VALIDATION, HttpStatus.BAD_REQUEST);
  }

  public JwtMalformedException(ErrorCode error) {
    super(error.getCode(), error.getMessage(), ErrorType.VALIDATION, HttpStatus.BAD_REQUEST);
  }
}
