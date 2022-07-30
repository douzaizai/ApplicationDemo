package com.example.demo.exception;

public enum ErrorCodeEnum {
  UNKNOW_EXCEPTION(1000, "unknow exception"),
  USER_NOT_EXISTS(2000, "user not exists"),
  WRONG_PASSWORD(2001,"wrong password");

  private final int errorCode;
  private final String errorMsg;

  ErrorCodeEnum(int errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  public int getErrorCode(){
    return errorCode;
  }

  public String getErrorMsg(){
    return errorMsg;
  }
}
