package com.msd.utrip.exception;

import com.msd.utrip.constant.enums.ErrorCode;
import com.msd.utrip.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class FileUploadException extends ApplicationException {
  public FileUploadException(String message) {
    super(
        ErrorCode.FILE_UPLOAD_EXCEPTION.getCode(),
        message,
        ErrorType.INTERNAL,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
