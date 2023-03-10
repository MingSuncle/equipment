package com.example.equipmentmanagementspring.pojo.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRequestBody {

  @Data
  @Builder
  public static class GetUserListReq {
    private long pageSize;
    private long currentPage;
    // 匹配姓名或账号
    private String inputKey;
    // 匹配类型
    private List<Integer> selectRoleId;
  }

  @Data
  @Builder
  public static class VerifyPasswordReq {
    private String userId;
    private String password;
  }

  @Data
  @Builder
  public static class ChangePasswordReq {
    private String userId;
    private String newPassword;
  }


}
