package com.example.equipmentmanagementspring.deviceConfig.service;

import com.example.equipmentmanagementspring.deviceConfig.entity.BoxInformationEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

public interface BoxInformationService extends IMppService<BoxInformationEntity> {


    Integer activateBox(String boxId);

    Integer boxState(String boxId);
}
