package com.example.equipmentmanagementspring.deviceConfig.dao;


import com.example.equipmentmanagementspring.deviceConfig.entity.AreaEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreaDao extends MppBaseMapper<AreaEntity> {
    List<AreaEntity> getArea(String boxId,String ipcId,Integer channelId);

    Integer getAreaNum(String boxId,String ipcId,Integer channelId);
}
