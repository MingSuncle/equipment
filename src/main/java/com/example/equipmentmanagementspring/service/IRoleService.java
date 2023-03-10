package com.example.equipmentmanagementspring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipmentmanagementspring.entity.Role;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
public interface IRoleService extends IService<Role> {

  List<Role> getAllRole();

}
