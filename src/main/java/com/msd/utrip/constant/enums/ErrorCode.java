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
  RESOURCE_NOT_FOUND(10010, "Resource not found"),

  LOGIN_INVALID_CODE(10110, "Login invalid"),
  ROLE_NOT_SUPPORTED_CODE(10111, "Provided Role not supported by the system"),
  JWT_INVALID_CODE(10112, "JWT invalid"),
  LOGIN_EXISTS_CODE(10113, "Login or tax number already in use"),
  REFRESH_TOKEN_INVALID_CODE(10114, "Refresh token invalid"),
  OTP_EXPIRED_CODE(10115, "OTP invalid"),
  PASSWORD_INVALID_CODE(10116, "Password invalid"),
  ENTITY_NOT_FOUND_CODE(10117, "Entity not found"),
  TOUR_NOT_FOUND_OR_FULL_CODE(10118, "Tour id invalid or all places are full"),
  TABLE_NOT_AVAILABLE_CODE(10118, "Table not available"),
  INVALID_MEAL_ID_PROVIDED_CODE(10120, "Invalid meal id provided in the list"),
  INVALID_AGENCY_TO_REVIEW(10121, "This user cannot leave review for this agency"),
  FILE_UPLOAD_EXCEPTION(10122, "File upload failed"),
  CATEGORY_INVALID_EXCEPTION(10123, "Category invalid"),
  COUNTRY_INVALID_EXCEPTION(10124, "Country invalid"),
  REGION_INVALID_EXCEPTION(10125, "Region invalid"),

  ADMIN_LOGIN_INVALID(10125, "Admin login invalid");

  final int code;
  final String message;

  ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
