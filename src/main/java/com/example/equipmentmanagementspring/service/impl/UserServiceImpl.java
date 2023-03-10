package com.example.equipmentmanagementspring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.equipmentmanagementspring.entity.Project;
import com.example.equipmentmanagementspring.entity.UserProject;
import com.example.equipmentmanagementspring.exception.ProjectNotFoundException;
import com.example.equipmentmanagementspring.exception.UserNotFoundException;
import com.example.equipmentmanagementspring.mapper.ProjectMapper;
import com.example.equipmentmanagementspring.mapper.UserProjectMapper;
import com.example.equipmentmanagementspring.pojo.request.UserRequestBody;
import com.example.equipmentmanagementspring.entity.User;
import com.example.equipmentmanagementspring.mapper.UserMapper;
import com.example.equipmentmanagementspring.pojo.response.UserResponseBody;
import com.example.equipmentmanagementspring.service.IUserService;
import com.example.equipmentmanagementspring.utils.PasswordMD5;
import com.example.equipmentmanagementspring.utils.Roles;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  @Resource
  UserMapper userMapper;

  @Resource
  UserProjectMapper userProjectMapper;

  @Resource
  ProjectMapper projectMapper;

  /**
   * 获取用户列表
   */
  public UserResponseBody.GetUserListRes getUserList(UserRequestBody.GetUserListReq req) {
    // 构造分页
    Page<User> pageHelper = new Page<>(req.getCurrentPage(), req.getPageSize());
    // 构造查询规则
    LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
    userLambdaQueryWrapper.in(req.getSelectRoleId() != null, User::getRoleId, req.getSelectRoleId())
        .and(req.getInputKey() != null && !"".equals(req.getInputKey()), x -> x
            .like(User::getAccount, req.getInputKey())
            .or()
            .like(User::getName, req.getInputKey()));
    // 查询
    Page<User> userPage = userMapper.selectPage(pageHelper, userLambdaQueryWrapper);
    // 构造返回体
    return UserResponseBody.GetUserListRes.builder().userList(userPage.getRecords())
        .total(userPage.getTotal())
        .build();
  }

  /**
   * 添加用户
   */
  public int addUser(User user) {
    user.setPassword(PasswordMD5.getPasswordMD5(user.getPassword()));
    return userMapper.insert(user);
  }

  /**
   * 修改用户
   */
  public int updateUser(User user) {
    return userMapper.updateById(user);
  }

  /**
   * 删除用户
   */
  public int deleteUser(String userId) {
    return userMapper.deleteById(userId);
  }

  /**
   * 验证密码
   */
  public boolean verifyPassword(UserRequestBody.VerifyPasswordReq req) {
    User user = userMapper.selectById(req.getUserId());
    if (user == null) {
      throw new UserNotFoundException();
    }
    if (!user.getPassword().equals(PasswordMD5.getPasswordMD5(req.getPassword()))) {
      return false;
    }
    return true;
  }

  /**
   * 修改密码
   */
  public int changePassword(UserRequestBody.ChangePasswordReq req) {
    User user = userMapper.selectById(req.getUserId());
    if (user == null) {
      throw new UserNotFoundException();
    }

    return userMapper.updateById(User.builder()
        .userId(req.getUserId())
        .password(PasswordMD5.getPasswordMD5(req.getNewPassword()))
        .build());
  }


  /**
   * 获取此项目未授权的项目管理员的列表
   *
   * @param projectId
   * @return
   */
  public List<User> getUnauthProjectUser(String projectId) {
    // 如果projectId没有，就直接返回全部的，否则需要剔除已授权的
    if (projectId == null || "".equals(projectId)) {
      return userMapper.selectList(new LambdaQueryWrapper<User>()
          .eq(User::getRoleId, Roles.PROJECT.getAuthority()));
    }
    Project project = projectMapper.selectById(projectId);
    if (project == null) {
      throw new ProjectNotFoundException();
    }
    List<UserProject> userProjects = userProjectMapper.selectList(new LambdaQueryWrapper<UserProject>()
        .eq(UserProject::getProjectId, projectId));
    List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>()
        .eq(User::getRoleId, Roles.PROJECT.getAuthority()));
    HashSet<String> authUsers = new HashSet<>();
    for (UserProject userProject : userProjects) {
      authUsers.add(userProject.getUserId());
    }
    for (int i = 0; i < users.size(); i++) {
      if (authUsers.contains(users.get(i).getUserId())) {
        users.remove(i);
        i--;
      }
    }
    return users;
  }


}
