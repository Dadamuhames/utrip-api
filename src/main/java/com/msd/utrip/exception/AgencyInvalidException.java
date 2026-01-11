package com.msd.utrip.exception;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class AgencyInvalidException extends ApplicationException {

  public AgencyInvalidException() {
    super(
        ErrorCode.INVALID_AGENCY_TO_REVIEW.getCode(),
        ErrorCode.INVALID_AGENCY_TO_REVIEW.getMessage(),
        ErrorType.VALIDATION,
        HttpStatus.BAD_REQUEST);
  }
}
