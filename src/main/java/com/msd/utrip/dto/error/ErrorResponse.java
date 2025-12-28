package com.msd.utrip.dto.error;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.ErrorType;
import com.msd.utrip.exception.ApplicationException;

import java.util.List;

public record ErrorResponse(
    int code, String message, ErrorType type, List<String> validationErrors) {

  public static ErrorResponse of(ErrorCode error, String message, ErrorType type) {
    return new ErrorResponse(error.getCode(), message, type, null);
  }

    public static ErrorResponse of(ErrorCode error, ErrorType type, List<String> errors) {
        return new ErrorResponse(error.getCode(), error.getMessage(), type, errors);
    }


    public static ErrorResponse of(int code, String message, ErrorType type) {
    return new ErrorResponse(code, message, type, null);
  }

  public static ErrorResponse of(ApplicationException ex) {
    return new ErrorResponse(ex.getCode(), ex.getMessage(), ex.getErrorType(), null);
  }

  public static ErrorResponse of(int code, String message, ErrorType type, List<String> error) {
    return new ErrorResponse(code, message, type, error);
  }
}
