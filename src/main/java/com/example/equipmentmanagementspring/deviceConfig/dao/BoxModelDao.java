package com.example.equipmentmanagementspring.deviceConfig.dao;

import com.example.equipmentmanagementspring.deviceConfig.entity.BoxModelEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@EnableMPP
public interface BoxModelDao extends MppBaseMapper<BoxModelEntity> {

    /***
     * 过渡借口，只考虑每个盒子仅存在一个模型
     * @param boxId
     * @return
     */
    BoxModelEntity getOne(String boxId);
}
