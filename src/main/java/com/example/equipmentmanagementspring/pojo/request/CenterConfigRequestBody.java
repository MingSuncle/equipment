package com.example.equipmentmanagementspring.pojo.request;

import lombok.Data;

@Data
public class CenterConfigRequestBody {

  @Data
  public static class AddCenterConfig {
    private String centerIp;

    private String projectId;

    private String centerThirdPartyUrls;
  }

  @Data
  public static class UpdateCenterConfig {

    private String centerId;

    private String centerIp;

    private String projectId;

    private String centerThirdPartyUrls;

    private Integer centerState;
  }
}
