package com.example.equipmentmanagementspring.pojo.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ProjectRequestBody {

  @Data
  @Builder
  public static class GetProjectListReq {
    private String userId;
    private long pageSize;
    private long currentPage;
    // 匹配项目名称
    private String inputKey;
    // 建设单位
    private List<String> selectProjectEmployer;
    // 施工单位
    private List<String> selectProjectContractor;
  }
}
