package com.example.equipmentmanagementspring.pojo.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UserProjectRequestBody {

  @Data
  @Builder
  public static class GetAuthorizedProjectReq {
    private String userId;
//    private long currentPage;
//    private long pageSize;
  }

  @Data
  @Builder
  public static class GetAuthorizedUserReq {
    private String projectId;
//    private Long currentPage;
//    private long pageSize;
  }

  @Data
  @Builder
  public static class UpdateAuthorization {
    private List<String> userId;
    private List<String> projectId;
    // 0 用户授权项目 1 项目授权用户
    private Integer type;
  }
}
