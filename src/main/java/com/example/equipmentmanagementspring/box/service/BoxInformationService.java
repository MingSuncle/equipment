package com.example.equipmentmanagementspring.box.service;

import com.example.equipmentmanagementspring.box.entity.BoxInformationEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

public interface BoxInformationService extends IMppService<BoxInformationEntity> {


    Integer activateBox(String boxId);

    Integer boxState(String boxId);
}
