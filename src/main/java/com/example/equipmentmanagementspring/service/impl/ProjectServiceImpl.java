package com.example.equipmentmanagementspring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipmentmanagementspring.entity.Project;
import com.example.equipmentmanagementspring.entity.User;
import com.example.equipmentmanagementspring.entity.UserProject;
import com.example.equipmentmanagementspring.exception.UserNotFoundException;
import com.example.equipmentmanagementspring.mapper.ProjectMapper;
import com.example.equipmentmanagementspring.mapper.UserMapper;
import com.example.equipmentmanagementspring.mapper.UserProjectMapper;
import com.example.equipmentmanagementspring.pojo.request.ProjectRequestBody;
import com.example.equipmentmanagementspring.pojo.response.ProjectResponseBody;
import com.example.equipmentmanagementspring.service.IProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

  @Resource
  ProjectMapper projectMapper;

  @Resource
  UserMapper userMapper;

  @Resource
  UserProjectMapper userProjectMapper;

  /**
   * 根据userId获取项目列表
   */
  public ProjectResponseBody.GetProjectListRes getProjectList(ProjectRequestBody.GetProjectListReq req) {
    // 先获取用户权限
    User user = userMapper.selectById(req.getUserId());
    if (user == null) {
      throw new UserNotFoundException();
    }

    // 是超级管理员
    if (user.getRoleId() == 0) {
      Page<Project> pageHelper = new Page<>(req.getCurrentPage(), req.getPageSize());
      LambdaQueryWrapper<Project> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
      projectLambdaQueryWrapper.like(req.getInputKey() != null && !"".equals(req.getInputKey()), Project::getProjectName, req.getInputKey())
          .and(req.getSelectProjectContractor() != null, x -> x
              .in(Project::getProjectContractor, req.getSelectProjectContractor()))
          .and(req.getSelectProjectEmployer() != null, x -> x
              .in(Project::getProjectEmployer, req.getSelectProjectEmployer()));
      Page<Project> projectPage = projectMapper.selectPage(pageHelper, projectLambdaQueryWrapper);
      return ProjectResponseBody.GetProjectListRes.builder()
          .total(projectPage.getTotal())
          .projectList(projectPage.getRecords())
          .build();
    }
    // 项目管理员和高级管理员
    // 先获取这个人的项目id列表，再去查项目
    Page<UserProject> pageHelper = new Page<>(req.getCurrentPage(), req.getPageSize());
    Page<UserProject> userProjectPage = userProjectMapper.selectPage(pageHelper,
        new LambdaQueryWrapper<UserProject>()
            .eq(UserProject::getUserId, req.getUserId())
            .select(UserProject::getProjectId));
    ArrayList<String> projects = new ArrayList<>();
    for (UserProject userProject : userProjectPage.getRecords()) {
      projects.add(userProject.getProjectId());
    }
    Page<Project> pageHelper1 = new Page<>(req.getCurrentPage(), req.getPageSize());
    LambdaQueryWrapper<Project> projectLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
    projectLambdaQueryWrapper1
        .in(Project::getProjectId, projects).like(req.getInputKey() != null && !"".equals(req.getInputKey()), Project::getProjectName, req.getInputKey())
        .and(req.getSelectProjectContractor() != null, x -> x
            .in(Project::getProjectContractor, req.getSelectProjectContractor()))
        .and(req.getSelectProjectEmployer() != null, x -> x
            .in(Project::getProjectEmployer, req.getSelectProjectEmployer()));
    Page<Project> projectPage1 = projectMapper.selectPage(pageHelper1, projectLambdaQueryWrapper1);
    return ProjectResponseBody.GetProjectListRes.builder()
        .total(projectPage1.getTotal())
        .projectList(projectPage1.getRecords())
        .build();
  }

  /**
   * 添加项目
   */
  public int addProject(Project project) {
    return projectMapper.insert(project);
  }

  /**
   * 修改项目
   */
  public int updateProject(Project project) {
    return projectMapper.updateById(project);
  }

  /**
   * 删除项目
   * 需要设置事务
   */
  @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
  public int deleteProject(String projectId) {
    int res = 0;
    try {
      //出现异常
      userProjectMapper.delete(new LambdaQueryWrapper<UserProject>().eq(UserProject::getProjectId, projectId));
      res = projectMapper.deleteById(projectId);
    } catch (Exception e) {
      //设置手动回滚
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      throw new RuntimeException(e.getMessage());
    }
    return res;
  }


  /**
   * 获取施工单位和建设单位列表
   *
   * @return
   */
  public ProjectResponseBody.GetEmployerAndContractor getEmployerAndContractor() {
    List<Project> employers = projectMapper.selectList(new QueryWrapper<Project>().select("distinct project_employer"));
    List<Project> contractors = projectMapper.selectList(new QueryWrapper<Project>().select("distinct project_contractor"));
    List<String> employerList = new ArrayList<>();
    List<String> contractorList = new ArrayList<>();
    for (Project project : employers) {
      employerList.add(project.getProjectEmployer());
    }
    for (Project project : contractors) {
      contractorList.add(project.getProjectContractor());
    }
    return ProjectResponseBody.GetEmployerAndContractor.builder()
        .contractorList(contractorList)
        .employerList(employerList)
        .build();
  }

}
