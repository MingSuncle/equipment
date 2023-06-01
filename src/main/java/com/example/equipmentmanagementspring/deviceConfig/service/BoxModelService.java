package com.example.equipmentmanagementspring.deviceConfig.service;

import com.example.equipmentmanagementspring.deviceConfig.entity.BoxModelEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

public interface BoxModelService extends IMppService<BoxModelEntity> {
    BoxModelEntity getBoxModel(String boxId,String modelId);

    BoxModelEntity getOne(String boxId);

    Integer confirmBoxModelVersion(String boxId,String currentVersion);
}
