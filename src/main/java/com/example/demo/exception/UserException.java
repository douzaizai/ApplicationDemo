package com.example.demo.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserException extends BaseException {
  public UserException(int errorCode, String errorMsg) {
    super(errorCode, errorMsg);
  }
}
