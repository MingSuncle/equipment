package com.example.equipmentmanagementspring.pojo.response;

import com.example.equipmentmanagementspring.entity.Project;
import com.example.equipmentmanagementspring.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class UserProjectResponseBody {

  @Data
  @Builder
  public static class GetAuthorizedProjectRes {
    private long total;
    private List<Project> projectsForUser;
  }

  @Data
  @Builder
  public static class GetAuthorizedUserRes {
    private long total;
    private List<User> UsersForProject;
    // 负责人
    private User principal;
  }
}
