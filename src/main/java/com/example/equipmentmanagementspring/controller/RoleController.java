package com.example.equipmentmanagementspring.controller;


import com.example.equipmentmanagementspring.entity.Role;
import com.example.equipmentmanagementspring.service.IRoleService;
import com.example.equipmentmanagementspring.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

  @Resource
  IRoleService roleService;

  Logger logger = Logger.getLogger(RoleController.class);

  @RequestMapping("/getAllRole")
  public Result<?> getAllRole() {
    logger.info("/role/getAllRole");
    try {
      List<Role> allRole = roleService.getAllRole();
      HashMap<String, Object> result = new HashMap<>();
      result.put("total", allRole.size());
      result.put("allRole", allRole);
      return Result.success(result);
    } catch (Exception e) {
      logger.debug(e.getMessage());
      return Result.error();
    }
  }

}

