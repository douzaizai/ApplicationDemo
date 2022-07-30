package com.example.demo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserApplyRequest {

  @NotBlank(message = "userName can not be blank")
  private String userName;
  @NotBlank(message = "password can not be blank")
  private String password;

}
