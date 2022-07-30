package com.example.demo.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
  private int errorCode;
  private String errorMsg;

  public BaseException(){}

  public BaseException(int errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }
}
