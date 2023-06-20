package com.example.equipmentmanagementspring.deviceConfig.dao;


import com.example.equipmentmanagementspring.deviceConfig.entity.ProcessParam;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessParamDao extends MppBaseMapper<ProcessParam> {

    List<ProcessParam> getAll();
}
