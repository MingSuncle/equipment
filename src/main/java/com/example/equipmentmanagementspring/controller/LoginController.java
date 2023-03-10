package com.example.equipmentmanagementspring.controller;

import com.example.equipmentmanagementspring.exception.UserInvalidException;
import com.example.equipmentmanagementspring.pojo.request.LoginRequestBody;
import com.example.equipmentmanagementspring.exception.PasswordErrorException;
import com.example.equipmentmanagementspring.exception.SignTokenException;
import com.example.equipmentmanagementspring.exception.UserNotFoundException;
import com.example.equipmentmanagementspring.service.LoginService;
import com.example.equipmentmanagementspring.pojo.response.LoginResponseBody;
import com.example.equipmentmanagementspring.utils.Result;
import com.example.equipmentmanagementspring.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class LoginController {
  @Resource
  private LoginService loginService;

  Logger logger = Logger.getLogger(LoginController.class);

  @PostMapping("login")
  public Result<?> login(LoginRequestBody loginRequestBody) {
    logger.info("/login");
    try {
      LoginResponseBody res = loginService.login(loginRequestBody);
      return Result.success(res);
    } catch (UserInvalidException e) {
      logger.warn(e.getMessage());
      return Result.error(e.getMessage());
    } catch (PasswordErrorException e) {
      logger.warn("login: passwordError!");
      return Result.error(ResultEnum.PASSWORD_ERROR);
    } catch (UserNotFoundException e) {
      logger.warn("login: userNotFound!");
      return Result.error(ResultEnum.USER_NOT_FOUND);
    } catch (SignTokenException e) {
      logger.warn("login: signTokenError!");
      return Result.error(ResultEnum.SIGN_TOKEN_ERROR);
    } catch (Exception e) {
      logger.error("login：" + e.getMessage());
      return Result.error();
    }
  }
}
