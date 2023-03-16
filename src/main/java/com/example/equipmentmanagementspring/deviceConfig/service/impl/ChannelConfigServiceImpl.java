package com.example.equipmentmanagementspring.deviceConfig.service.impl;


import com.example.equipmentmanagementspring.deviceConfig.entity.ChannelEntity;
import com.example.equipmentmanagementspring.deviceConfig.dao.ChannelDao;
import com.example.equipmentmanagementspring.deviceConfig.service.ChannelService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ChannelConfigService")
public class ChannelConfigServiceImpl extends
        MppServiceImpl<ChannelDao, ChannelEntity> implements ChannelService {
    @Override
    public List<ChannelEntity> getChannel(String ipcId,String boxId) {
       return baseMapper.getChannel(ipcId,boxId);
    }

    @Override
    public Integer getChannelNum(String ipcId,String boxId) {
        return baseMapper.getChannelNum(ipcId,boxId);
    }
}
