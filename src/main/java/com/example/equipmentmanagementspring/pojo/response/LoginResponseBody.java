package com.example.equipmentmanagementspring.pojo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseBody {
  private String userId;
  private String account;
  private String name;
  private Integer roleId;
  private String token;
  private String phone;
  private String department;
  private String position;
}
