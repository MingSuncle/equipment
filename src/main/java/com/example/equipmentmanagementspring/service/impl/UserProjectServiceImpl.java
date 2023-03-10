package com.example.equipmentmanagementspring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.equipmentmanagementspring.entity.Project;
import com.example.equipmentmanagementspring.entity.User;
import com.example.equipmentmanagementspring.entity.UserProject;
import com.example.equipmentmanagementspring.exception.AuthorizationExistedException;
import com.example.equipmentmanagementspring.exception.ProjectNotFoundException;
import com.example.equipmentmanagementspring.exception.UserNotFoundException;
import com.example.equipmentmanagementspring.mapper.ProjectMapper;
import com.example.equipmentmanagementspring.mapper.UserMapper;
import com.example.equipmentmanagementspring.mapper.UserProjectMapper;
import com.example.equipmentmanagementspring.pojo.request.UserProjectRequestBody;
import com.example.equipmentmanagementspring.pojo.response.UserProjectResponseBody;
import com.example.equipmentmanagementspring.service.IUserProjectService;
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
public class UserProjectServiceImpl extends ServiceImpl<UserProjectMapper, UserProject> implements IUserProjectService {

  @Resource
  UserProjectMapper userProjectMapper;

  @Resource
  ProjectMapper projectMapper;

  @Resource
  UserMapper userMapper;

  /**
   * 项目授权
   */
  @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
  public int authorizeProject(UserProject userProject) {
    UserProject userProject1 = userProjectMapper.selectOne(new LambdaQueryWrapper<UserProject>()
        .eq(UserProject::getUserId, userProject.getUserId())
        .eq(UserProject::getProjectId, userProject.getProjectId()));
    if (userProject1 != null) {
      throw new AuthorizationExistedException();
    }
    int res = 0;
    try {
      projectMapper.updateById(Project.builder()
          .projectId(userProject.getProjectId())
          .userId(userProject.getUserId())
          .build());
      res = userProjectMapper.insert(userProject);
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      throw new RuntimeException(e.getMessage());
    }
    return res;
  }

  /**
   * 获取用户授权的项目
   */
  public UserProjectResponseBody.GetAuthorizedProjectRes getAuthorizedProject(UserProjectRequestBody.GetAuthorizedProjectReq req) {
    User user = userMapper.selectById(req.getUserId());
    if (user == null) {
      throw new UserNotFoundException();
    }

//    Page<UserProject> pageHelper = new Page<>(req.getCurrentPage(), req.getPageSize());
    List<UserProject> userProjectList = userProjectMapper.selectList(new LambdaQueryWrapper<UserProject>().eq(UserProject::getUserId, req.getUserId()));
    ArrayList<Project> projects = new ArrayList<>();
    for (UserProject userProject : userProjectList) {
      projects.add(projectMapper.selectById(userProject.getProjectId()));
    }
    return UserProjectResponseBody.GetAuthorizedProjectRes.builder()
        .total(userProjectList.size())
        .projectsForUser(projects)
        .build();
  }

  /**
   * 获取项目授权的用户
   */
  public UserProjectResponseBody.GetAuthorizedUserRes getAuthorizedUser(UserProjectRequestBody.GetAuthorizedUserReq req) {
    Project project = projectMapper.selectById(req.getProjectId());
    if (project == null) {
      throw new ProjectNotFoundException();
    }

//    Page<UserProject> pageHelper = new Page<>(req.getCurrentPage(), req.getPageSize());
    List<UserProject> userProjectList = userProjectMapper.selectList(new LambdaQueryWrapper<UserProject>().eq(UserProject::getProjectId, req.getProjectId()));
    ArrayList<User> users = new ArrayList<>();
    for (UserProject userProject : userProjectList) {
      users.add(userMapper.selectById(userProject.getUserId()));
    }
    // 需要把负责人单独查出来
    User user = null;
    if (project.getUserId() != null && project.getUserId().length() != 0) {
      user = userMapper.selectById(project.getUserId());
      if (user == null) {
        throw new UserNotFoundException();
      }
    }
    return UserProjectResponseBody.GetAuthorizedUserRes.builder()
        .total(userProjectList.size())
        .UsersForProject(users)
        .principal(user)
        .build();
  }

  /**
   * 撤销授权
   */
  @Transactional(noRollbackFor = {RuntimeException.class, Exception.class})
  public int revokeAuthorization(UserProject userProject) {
    Project project = projectMapper.selectById(userProject.getProjectId());
    if (project == null) {
      throw new ProjectNotFoundException();
    }
    int res = 0;
    try {
      projectMapper.updateById(Project.builder()
          .userId("")
          .projectId(userProject.getProjectId())
          .build());
      res = userProjectMapper.delete(new LambdaQueryWrapper<UserProject>()
          .eq(UserProject::getProjectId, userProject.getProjectId())
          .eq(UserProject::getUserId, userProject.getUserId()));
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      throw new RuntimeException(e.getMessage());
    }
    return res;
  }

  /**
   * 更新授权，可以是用户授权也可以是项目授权
   * @param updateAuthorization
   * @return
   */
  @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
  public void updateAuthorization(UserProjectRequestBody.UpdateAuthorization updateAuthorization) {
    if (updateAuthorization.getProjectId() == null || updateAuthorization.getUserId() == null ||
        (updateAuthorization.getProjectId().size() > 1 && updateAuthorization.getUserId().size() > 1)) {
      throw new RuntimeException("参数错误");
    }
    for (String userId : updateAuthorization.getUserId()) {
      User user = userMapper.selectById(userId);
      if (user == null) {
        throw new UserNotFoundException();
      }
    }
    for (String projectId: updateAuthorization.getProjectId()) {
      Project project = projectMapper.selectById(projectId);
      if (project == null) {
        throw new ProjectNotFoundException();
      }
    }
    try {
      // 用户授权项目
      if(updateAuthorization.getType() == 0) {
        if (updateAuthorization.getUserId().size() != 1) {
          throw new RuntimeException("参数错误");
        }
        // 将该用户的相关的项目全删掉，在全部加进去
        userProjectMapper.delete(new LambdaQueryWrapper<UserProject>()
            .eq(UserProject::getUserId, updateAuthorization.getUserId().get(0)));
        if (updateAuthorization.getProjectId() == null) {
          return;
        }
        for (String projectId : updateAuthorization.getProjectId()) {
          UserProject userProject = userProjectMapper.selectOne(new LambdaQueryWrapper<UserProject>()
              .eq(UserProject::getProjectId, projectId)
              .eq(UserProject::getUserId, updateAuthorization.getUserId().get(0)));
          if (userProject != null) {
            throw new AuthorizationExistedException();
          }
          userProjectMapper.insert(UserProject.builder()
              .userId(updateAuthorization.getUserId().get(0))
              .projectId(projectId)
              .build());
        }
      } else if (updateAuthorization.getType() == 1) {
        if (updateAuthorization.getProjectId().size() != 1) {
          throw new RuntimeException("参数错误");
        }
        userProjectMapper.delete(new LambdaQueryWrapper<UserProject>()
            .eq(UserProject::getProjectId, updateAuthorization.getProjectId().get(0)));
        if (updateAuthorization.getUserId() == null) {
          return;
        }
        for (String userId: updateAuthorization.getUserId()) {
          UserProject userProject = userProjectMapper.selectOne(new LambdaQueryWrapper<UserProject>()
              .eq(UserProject::getProjectId, updateAuthorization.getProjectId().get(0))
              .eq(UserProject::getUserId, userId));
          if (userProject != null) {
            throw new AuthorizationExistedException();
          }
          userProjectMapper.insert(UserProject.builder()
              .projectId(updateAuthorization.getProjectId().get(0))
              .userId(userId)
              .build());
        }
      }
    } catch (Exception e) {
      TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
      throw new RuntimeException(e.getMessage());
    }
  }
}
