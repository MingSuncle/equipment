package com.example.equipmentmanagementspring.deviceConfig.dao;

import com.example.equipmentmanagementspring.deviceConfig.entity.ModelInformationEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelInformationDao extends MppBaseMapper<ModelInformationEntity> {

    /**
     *查询全部模型
     */
    List<ModelInformationEntity> getAllModel();
}
