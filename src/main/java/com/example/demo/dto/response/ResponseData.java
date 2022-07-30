package com.example.demo.dto.response;

import lombok.Data;

@Data
public class ResponseData<T> {
  private T data;
  private int errorCode;
  private String errorMsg;

  public ResponseData(T data) {
    this.data = data;
  }

  public ResponseData(int errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

}
