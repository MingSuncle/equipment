package com.example.equipmentmanagementspring.controller;


import com.example.equipmentmanagementspring.exception.ProjectNotFoundException;
import com.example.equipmentmanagementspring.exception.UserNotFoundException;
import com.example.equipmentmanagementspring.pojo.request.UserRequestBody;
import com.example.equipmentmanagementspring.entity.User;
import com.example.equipmentmanagementspring.pojo.response.UserResponseBody;
import com.example.equipmentmanagementspring.service.IUserService;
import com.example.equipmentmanagementspring.utils.Result;
import com.example.equipmentmanagementspring.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
  @Resource
  private IUserService userService;

  Logger logger = Logger.getLogger(UserController.class);

  @RequestMapping("/getUserList")
//  @RequiresRoles(value = {"root", "project"})
  public Result<?> getUserList(UserRequestBody.GetUserListReq req) {
    logger.info("/user/getUserList:" + req);
    try {
      UserResponseBody.GetUserListRes res = userService.getUserList(req);
      HashMap<String, Object> result = new HashMap<>();
      result.put("userList", res.getUserList());
      result.put("total", res.getTotal());
      return Result.success(result);
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  @RequestMapping("/addUser")
//  @RequiresRoles(value = {"root", "project"})
  public Result<Void> addUser(User user) {
    logger.info("/user/addUser");
    try {
      int res = userService.addUser(user);
      if (res == 1) {
        return Result.success();
      } else {
        return Result.error();
      }
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  @RequestMapping("/updateUser")
  public Result<Void> updateUser(User user) {
    logger.info("/user/updateUser");
    try {
      int res = userService.updateUser(user);
      if (res == 1) {
        return Result.success();
      } else {
        return Result.error();
      }
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  @RequestMapping("/deleteUser")
  public Result<Void> deleteUser(String userId) {
    logger.info("/user/deleteUser");
    try {
      int res = userService.deleteUser(userId);
      if (res == 1) {
        return Result.success();
      } else {
        return Result.error();
      }
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  /**
   * 验证密码
   */
  @RequestMapping("/verifyPassword")
  public Result<?> verifyPassword(UserRequestBody.VerifyPasswordReq req) {
    logger.info("/user/verifyPassword");
    try {
      boolean res = userService.verifyPassword(req);
      HashMap<String, Boolean> result = new HashMap<>();
      result.put("isEqual", res);
      return Result.success(result);
    } catch (UserNotFoundException e) {
      logger.debug("user not found");
      return Result.error(ResultEnum.USER_NOT_FOUND);
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  @RequestMapping("/changePassword")
  public Result<Void> changePassword(UserRequestBody.ChangePasswordReq req) {
    logger.info("/user/changePassword");
    try {
      int res = userService.changePassword(req);
      if (res == 1) {
        return Result.success();
      } else {
        return Result.error();
      }
    } catch (UserNotFoundException e) {
      logger.debug(e.getMessage());
      return Result.error(ResultEnum.USER_NOT_FOUND);
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  /**
   * 获取此项目未授权的项目管理员列表
   *
   * @param projectId
   * @return
   */
  @RequestMapping("/getUnauthProjectUser")
  public Result<?> getUnauthProjectUser(String projectId) {
    logger.info("/user/getUnauthProjectUser");
    try {
      List<User> res = userService.getUnauthProjectUser(projectId);
      HashMap<String, Object> result = new HashMap<>();
      result.put("projectUserList", res);
      return Result.success(result);
    } catch (ProjectNotFoundException e) {
      logger.debug("project not found");
      return Result.error(e.getMessage());
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }


}

