package com.example.demo.exception;

import com.example.demo.dto.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseData<Object> handleException(HttpMessageNotReadableException e) {
    log.error("", e);
    return new ResponseData<>(1001, "request body can not be null");
  }

  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseData<Object> handleException(MethodArgumentNotValidException e) {
    String error = e.getBindingResult().getFieldError() != null ? e.getBindingResult().getFieldError().getDefaultMessage() : e.getMessage();
    log.error("", e);
    return new ResponseData<>(1002, error);
  }

  @ResponseBody
  @ExceptionHandler(value = BaseException.class)
  public ResponseData<Object> handleException(BaseException e) {
    return new ResponseData<>(e.getErrorCode(), e.getErrorMsg());
  }

  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public ResponseData<Object> handleException(Exception e) {
    log.error("", e);
    return new ResponseData<>(ErrorCodeEnum.UNKNOW_EXCEPTION.getErrorCode(), ErrorCodeEnum.UNKNOW_EXCEPTION.getErrorMsg());
  }
}