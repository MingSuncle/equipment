package com.example.equipmentmanagementspring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipmentmanagementspring.entity.Project;
import com.example.equipmentmanagementspring.pojo.request.ProjectRequestBody;
import com.example.equipmentmanagementspring.pojo.response.ProjectResponseBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
public interface IProjectService extends IService<Project> {

  ProjectResponseBody.GetProjectListRes getProjectList(ProjectRequestBody.GetProjectListReq req);

  int addProject(Project project);

  int updateProject(Project project);

  int deleteProject(String projectId);

  ProjectResponseBody.GetEmployerAndContractor getEmployerAndContractor();
}
