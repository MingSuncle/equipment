package com.example.equipmentmanagementspring.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {

  // 超级管理员
  ROOT(0, "root"),
  // 高级管理员
  PRIORITY(1, "priority"),
  // 项目管理员
  PROJECT(2, "project"),
  // 设备管理员
  FACILITY(3, "facility"),
  // 安全管理员
  SECURITY(4, "security");

  private int authority;
  private String role;
}
