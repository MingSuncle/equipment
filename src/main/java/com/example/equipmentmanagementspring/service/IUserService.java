package com.example.equipmentmanagementspring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipmentmanagementspring.pojo.request.UserRequestBody;
import com.example.equipmentmanagementspring.entity.User;
import com.example.equipmentmanagementspring.pojo.response.UserResponseBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
public interface IUserService extends IService<User> {

  UserResponseBody.GetUserListRes getUserList(UserRequestBody.GetUserListReq req);

  int addUser(User user);

  int updateUser(User user);

  int deleteUser(String userId);

  boolean verifyPassword(UserRequestBody.VerifyPasswordReq req);

  int changePassword(UserRequestBody.ChangePasswordReq req);

  List<User> getUnauthProjectUser(String projectId);

}
