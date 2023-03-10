package com.example.equipmentmanagementspring.controller;


import com.example.equipmentmanagementspring.entity.User;
import com.example.equipmentmanagementspring.entity.UserProject;
import com.example.equipmentmanagementspring.exception.AuthorizationExistedException;
import com.example.equipmentmanagementspring.exception.AuthorizationNotFoundException;
import com.example.equipmentmanagementspring.exception.ProjectNotFoundException;
import com.example.equipmentmanagementspring.exception.UserNotFoundException;
import com.example.equipmentmanagementspring.pojo.request.UserProjectRequestBody;
import com.example.equipmentmanagementspring.pojo.response.UserProjectResponseBody;
import com.example.equipmentmanagementspring.service.IUserProjectService;
import com.example.equipmentmanagementspring.utils.Result;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

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
@RequestMapping("/userProject")
public class UserProjectController {

  @Resource
  IUserProjectService userProjectService;

  Logger logger = Logger.getLogger(UserProjectController.class);

  @RequestMapping("/authorizeProject")
//  @RequiresRoles(value = {"root", "project"})
  public Result<Void> authorizeProject(UserProject userProject) {
    logger.info("/userProject/authorizeProject");
    try {
      int res = userProjectService.authorizeProject(userProject);
      if (res == 1) {
        return Result.success();
      } else {
        return Result.error();
      }
    } catch (AuthorizationExistedException e) {
      logger.debug(e.getMessage());
      return Result.error(e.getMessage());
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  /**
   * 撤销授权
   */
  @RequestMapping("/revokeAuthorization")
//  @RequiresRoles(value = {"root", "project"})
  public Result<Void> revokeProject(UserProject userProject) {
    logger.info("/userProject/revokeAuthorization");
    try {
      int res = userProjectService.revokeAuthorization(userProject);
      if (res == 1) {
        return Result.success();
      } else {
        throw new AuthorizationNotFoundException();
      }
    } catch (ProjectNotFoundException e) {
      logger.debug(e.getMessage());
      return Result.error(e.getMessage());
    } catch (AuthorizationNotFoundException e) {
      logger.debug(e.getMessage());
      return Result.error(e.getMessage());
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  /**
   * 查询当前用户授权的项目
   */
  @RequestMapping("/getAuthorizedProject")
  public Result<?> getAuthorizedProject(UserProjectRequestBody.GetAuthorizedProjectReq req) {
    logger.info("/userProject/getAuthorizedProject");
    try {
      UserProjectResponseBody.GetAuthorizedProjectRes res = userProjectService.getAuthorizedProject(req);
      HashMap<String, Object> result = new HashMap<>();
      result.put("total", res.getTotal());
      result.put("projectsForUser", res.getProjectsForUser());
      return Result.success(result);
    } catch (UserNotFoundException e) {
      logger.debug(e.getMessage());
      return Result.error(e.getMessage());
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  /**
   * 查询当前项目授权的用户
   */
  @RequestMapping("/getAuthorizedUser")
  public Result<?> getAuthorizedUser(UserProjectRequestBody.GetAuthorizedUserReq req) {
    logger.info("/userProject/getAuthorizedUser");
    try {
      UserProjectResponseBody.GetAuthorizedUserRes res = userProjectService.getAuthorizedUser(req);
      HashMap<String, Object> result = new HashMap<>();
      result.put("total", res.getTotal());
      result.put("usersForProject", res.getUsersForProject());
      result.put("principal", res.getPrincipal());
      return Result.success(result);
    } catch (ProjectNotFoundException e) {
      logger.debug(e.getMessage());
      return Result.error(e.getMessage());
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  @RequestMapping("/updateAuthorization")
  public Result<Void> updateAuthorization(UserProjectRequestBody.UpdateAuthorization updateAuthorization) {
    logger.info("/userProject/updateAuthorization");
    try {
      userProjectService.updateAuthorization(updateAuthorization);
      return Result.success();
    } catch (ProjectNotFoundException e) {
      logger.debug(e.getMessage());
      return Result.error(e.getMessage());
    } catch (UserNotFoundException e) {
      logger.debug(e.getMessage());
      return Result.error(e.getMessage());
    } catch (Exception e) {
      logger.debug(e.getMessage());
      if (e.getMessage() != null && e.getMessage().length() != 0) {
        return Result.error(e.getMessage());
      } else {
        return Result.error();
      }
    }
  }

}

