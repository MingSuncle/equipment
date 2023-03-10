package com.example.equipmentmanagementspring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipmentmanagementspring.entity.Role;
import com.example.equipmentmanagementspring.mapper.RoleMapper;
import com.example.equipmentmanagementspring.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

  @Resource
  RoleMapper roleMapper;

  public List<Role> getAllRole() {
    return roleMapper.selectList(new LambdaQueryWrapper<Role>());
  }

}
