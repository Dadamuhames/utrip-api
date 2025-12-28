package com.msd.utrip.constant.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
  INTERNAL_SERVICE_ERROR_CODE(10001, "System not available"),
  EXTERNAL_SERVICE_FAILED_ERROR_CODE(10002, "External service not available"),
  HANDLER_NOT_FOUND_ERROR_CODE(10003, "Handler not found"),
  JSON_NOT_VALID_ERROR_CODE(10004, "Json not valid"),
  VALIDATION_ERROR_CODE(10005, "Validation error"),
  INVALID_REQUEST_PARAM_ERROR_CODE(10006, "Invalid request param"),
  INTERNAL_TIMEOUT_ERROR_CODE(10007, "Internal timeout"),
  METHOD_NOT_SUPPORTED_ERROR_CODE(10008, "Method not supported"),
  MISSING_REQUEST_HEADER_ERROR_CODE(10009, "Missing request header"),
  USER_INVALID_CODE(10010, "User invalid"),
  ROLE_NOT_SUPPORTED_CODE(10011, "Provided Role not supported by the system"),
  JWT_INVALID_CODE(10012, "JWT invalid"),
  LOGIN_OR_TAX_NUMBER_EXISTS_CODE(10013, "Login or tax number already in use"),
  REFRESH_TOKEN_INVALID_CODE(10014, "Refresh token invalid"),
  OTP_EXPIRED_CODE(10015, "OTP invalid"),
  PASSWORD_INVALID_CODE(10016, "Password invalid"),
  ENTITY_NOT_FOUND_CODE(10404, "Entity not found"),
  TOUR_NOT_FOUND_OR_FULL_CODE(10404, "Tour id invalid or all places are full"),
  TABLE_NOT_AVAILABLE_CODE(10018, "Table not available"),
  INVALID_MEAL_ID_PROVIDED_CODE(10019, "Invalid meal id provided in the list");

  final int code;
  final String message;

  ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
