package com.example.equipmentmanagementspring.deviceConfig.service;


import com.example.equipmentmanagementspring.deviceConfig.entity.AreaEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;

public interface AreaService extends IMppService<AreaEntity> {
    List<AreaEntity> getArea(String boxId,String ipcId,Integer channelId);

    Integer getAreaNum(String boxId,String ipcId,Integer channelId);
}
