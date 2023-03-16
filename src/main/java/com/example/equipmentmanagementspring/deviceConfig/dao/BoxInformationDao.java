package com.example.equipmentmanagementspring.deviceConfig.dao;


import com.example.equipmentmanagementspring.deviceConfig.entity.BoxInformationEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoxInformationDao extends MppBaseMapper<BoxInformationEntity> {

    /**
     * 查询盒子激活状态
     *
     * @return 盒子是否激活
     */
    Boolean boxState(String boxId);

    /**
     * 激活盒子
     *
     * @return null
     */
    Boolean activateBox(String boxId);

}
