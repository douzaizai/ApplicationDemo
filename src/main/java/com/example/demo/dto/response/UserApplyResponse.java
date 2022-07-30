package com.example.demo.dto.response;

import com.example.demo.serializer.Time2StringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserApplyResponse {
  private String userId;
  @JsonSerialize(using = Time2StringSerializer.class)
  private LocalDateTime createdTime;
}
