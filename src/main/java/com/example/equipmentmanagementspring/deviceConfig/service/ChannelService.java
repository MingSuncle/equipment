package com.example.equipmentmanagementspring.deviceConfig.service;

import com.example.equipmentmanagementspring.deviceConfig.entity.ChannelEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;

public interface ChannelService extends IMppService<ChannelEntity> {

    List<ChannelEntity> getChannel(String ipcId,String boxId);

    Integer getChannelNum(String ipcId,String boxId);
}
