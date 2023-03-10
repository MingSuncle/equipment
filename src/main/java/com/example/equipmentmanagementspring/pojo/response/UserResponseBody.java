package com.example.equipmentmanagementspring.pojo.response;

import com.example.equipmentmanagementspring.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class UserResponseBody {

  @Data
  @Builder
  public static class GetUserListRes {
    private List<User> userList;
    private long total;
  }

}
