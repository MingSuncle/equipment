package com.example.equipmentmanagementspring.pojo.response;

import com.example.equipmentmanagementspring.entity.Project;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ProjectResponseBody {

  @Data
  @Builder
  public static class GetProjectListRes {
    private long total;
    private List<Project> projectList;
  }

  @Data
  @Builder
  public static class GetEmployerAndContractor {
    private List<String> employerList;
    private List<String> contractorList;
  }

}
