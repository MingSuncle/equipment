package com.example.equipmentmanagementspring.box.dao;

import com.example.equipmentmanagementspring.box.entity.ModelInformationEntity;
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
