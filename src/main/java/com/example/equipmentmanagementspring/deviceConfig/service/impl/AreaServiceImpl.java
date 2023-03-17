package com.example.equipmentmanagementspring.deviceConfig.service.impl;

import com.example.equipmentmanagementspring.deviceConfig.entity.AreaEntity;
import com.example.equipmentmanagementspring.deviceConfig.dao.AreaDao;
import com.example.equipmentmanagementspring.deviceConfig.service.AreaService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AreaService")
public class AreaServiceImpl extends
        MppServiceImpl<AreaDao, AreaEntity> implements AreaService {

    @Override
    public List<AreaEntity> getArea(String boxId, String ipcId, Integer channelId) {
        return baseMapper.getArea(boxId,ipcId,channelId);
    }

    @Override
    public Integer getAreaNum(String boxId, String ipcId, Integer channelId) {
        return baseMapper.getAreaNum(boxId,ipcId,channelId);
    }
}
