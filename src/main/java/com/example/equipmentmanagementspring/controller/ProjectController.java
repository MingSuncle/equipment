package com.example.equipmentmanagementspring.controller;


import com.example.equipmentmanagementspring.entity.Project;
import com.example.equipmentmanagementspring.exception.ProjectNotFoundException;
import com.example.equipmentmanagementspring.exception.UserNotFoundException;
import com.example.equipmentmanagementspring.pojo.request.ProjectRequestBody;
import com.example.equipmentmanagementspring.pojo.response.ProjectResponseBody;
import com.example.equipmentmanagementspring.service.IProjectService;
import com.example.equipmentmanagementspring.utils.Result;
import com.example.equipmentmanagementspring.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {

  @Resource
  private IProjectService projectService;

  Logger logger = Logger.getLogger(ProjectController.class);

  /**
   * 获取某个用户的授权项目
   */
  @RequestMapping("/getProjectList")
  public Result<?> getProjectList(ProjectRequestBody.GetProjectListReq req) {
    logger.info("/project/getProjectList");
    try {
      ProjectResponseBody.GetProjectListRes res = projectService.getProjectList(req);
      HashMap<String, Object> result = new HashMap<>();
      result.put("total", res.getTotal());
      result.put("projectList", res.getProjectList());
      return Result.success(result);
    } catch (UserNotFoundException e) {
      logger.debug(e.getMessage());
      return Result.error(ResultEnum.USER_NOT_FOUND);
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

  @RequestMapping("/addProject")
//  @RequiresRoles(value = {"root"})
  public Result<Void> addProject(Project project) {
    logger.info("/project/addProject");
    try {
      int res = projectService.addProject(project);
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

  @RequestMapping("/updateProject")
//  @RequiresRoles(value = {"root", "project"})
  public Result<Void> updateProject(Project project) {
    logger.info("/project/updateProject");
    try {
      int res = projectService.updateProject(project);
      if (res == 1) {
        return Result.success();
      } else {
        return Result.error();
      }
    } catch (ProjectNotFoundException e) {
      logger.info(e.getMessage());
      return Result.error(e.getMessage());
    } catch (Exception e) {
      logger.info(e.getMessage());
      return Result.error();
    }
  }

  @RequestMapping("/deleteProject")
//  @RequiresRoles(value = {"root"})
  public Result<Void> deleteProject(String projectId) {
    logger.info("/project/deleteProject");
    try {
      int res = projectService.deleteProject(projectId);
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

  @RequestMapping("/getEmployerAndContractor")
  public Result<?> getEmployerAndContractor() {
    logger.info("/project/getEmployerAndContractor");
    try {
      ProjectResponseBody.GetEmployerAndContractor res = projectService.getEmployerAndContractor();
      HashMap<String, Object> result = new HashMap<>();
      result.put("employerList", res.getEmployerList());
      result.put("contractorList", res.getContractorList());
      return Result.success(result);
    } catch (UserNotFoundException e) {
      logger.debug(e.getMessage());
      return Result.error(ResultEnum.USER_NOT_FOUND);
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

}

