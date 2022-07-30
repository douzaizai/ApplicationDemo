package com.example.demo.service.impl;

import com.example.demo.dto.request.UserApplyRequest;
import com.example.demo.dto.response.UserApplyResponse;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
  @Override
  public UserApplyResponse userApply(UserApplyRequest request) {
    String userId = UUID.randomUUID().toString();
    LocalDateTime createdTime = LocalDateTime.now();
    UserApplyResponse response = new UserApplyResponse();
    response.setUserId(userId);
    response.setCreatedTime(createdTime);
    return response;
  }
}
