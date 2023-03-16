package com.example.equipmentmanagementspring.deviceConfig.dao;


import com.example.equipmentmanagementspring.deviceConfig.entity.ChannelEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
@EnableMPP
public interface ChannelDao extends MppBaseMapper<ChannelEntity> {

    List<ChannelEntity> getChannel(String ipcId,String boxId);

    Integer getChannelNum(String ipcId,String boxId);
}
