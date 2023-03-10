package com.example.equipmentmanagementspring.service;


import com.example.equipmentmanagementspring.pojo.request.LoginRequestBody;
import com.example.equipmentmanagementspring.pojo.response.LoginResponseBody;

public interface LoginService {
  LoginResponseBody login(LoginRequestBody loginRequestBody);
}
