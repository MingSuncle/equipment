package com.example.equipmentmanagementspring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.equipmentmanagementspring.exception.UserInvalidException;
import com.example.equipmentmanagementspring.pojo.request.LoginRequestBody;
import com.example.equipmentmanagementspring.entity.User;
import com.example.equipmentmanagementspring.exception.PasswordErrorException;
import com.example.equipmentmanagementspring.exception.SignTokenException;
import com.example.equipmentmanagementspring.exception.UserNotFoundException;
import com.example.equipmentmanagementspring.mapper.UserMapper;
import com.example.equipmentmanagementspring.service.LoginService;
import com.example.equipmentmanagementspring.pojo.response.LoginResponseBody;
import com.example.equipmentmanagementspring.utils.JWTUtil;
import com.example.equipmentmanagementspring.utils.PasswordMD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

  @Resource
  private UserMapper userMapper;

  @Resource
  private JWTUtil JWTUtil;

  @Override
  public LoginResponseBody login(LoginRequestBody loginRequestBody) {

    // 查询用户
    User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getAccount, loginRequestBody.getAccount()));
    if (user == null) {
      throw new UserNotFoundException();
    }

    if (user.getUserState() == 0) {
      throw new UserInvalidException();
    }

    // 需要加密后进行判断
    if (!user.getPassword().equals(PasswordMD5.getPasswordMD5(loginRequestBody.getPassword()))) {
      throw new PasswordErrorException();
    }

    String token;
    try {
      // 生成token
      token = this.JWTUtil.sign(user.getAccount());
    } catch (Exception e) {
      e.printStackTrace();
      throw new SignTokenException();
    }

    return LoginResponseBody.builder()
        .userId(user.getUserId())
        .name(user.getName())
        .account(user.getAccount())
        .phone(user.getPhoneNumber())
        .department(user.getDepartment())
        .position(user.getPost())
        .roleId(user.getRoleId())
        .token(token)
        .build();
  }
}
