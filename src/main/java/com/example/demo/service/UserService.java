package com.example.demo.service;

import com.example.demo.dto.request.UserApplyRequest;
import com.example.demo.dto.response.UserApplyResponse;

public interface UserService {

  UserApplyResponse userApply(UserApplyRequest request);

}
