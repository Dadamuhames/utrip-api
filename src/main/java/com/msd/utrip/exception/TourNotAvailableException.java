package com.msd.utrip.exception;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class TourNotAvailableException extends ApplicationException {

  public TourNotAvailableException() {
    super(
        ErrorCode.TOUR_NOT_FOUND_OR_FULL_CODE.getCode(),
        ErrorCode.TOUR_NOT_FOUND_OR_FULL_CODE.getMessage(),
        ErrorType.VALIDATION,
        HttpStatus.BAD_REQUEST);
  }
}
