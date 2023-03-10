package com.example.equipmentmanagementspring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipmentmanagementspring.entity.UserProject;
import com.example.equipmentmanagementspring.pojo.request.UserProjectRequestBody;
import com.example.equipmentmanagementspring.pojo.response.UserProjectResponseBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
public interface IUserProjectService extends IService<UserProject> {

  int authorizeProject(UserProject userProject);

  UserProjectResponseBody.GetAuthorizedProjectRes getAuthorizedProject(UserProjectRequestBody.GetAuthorizedProjectReq req);

  UserProjectResponseBody.GetAuthorizedUserRes getAuthorizedUser(UserProjectRequestBody.GetAuthorizedUserReq req);

  int revokeAuthorization(UserProject userProject);

  void updateAuthorization(UserProjectRequestBody.UpdateAuthorization updateAuthorization);
}
