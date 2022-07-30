package com.example.demo.controller;

import com.example.demo.dto.request.UserApplyRequest;
import com.example.demo.dto.response.UserApplyResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private UserService userService;

  @GetMapping("hello")
  public String hello(@RequestParam(name = "name") String name) {
    return "hello " + name;
  }

  @PostMapping("/user/apply")
  public UserApplyResponse userApply(@RequestBody @Valid UserApplyRequest request) {
    return userService.userApply(request);
  }

}
