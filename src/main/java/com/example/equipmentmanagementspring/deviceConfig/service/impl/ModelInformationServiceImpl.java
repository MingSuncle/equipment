package com.example.equipmentmanagementspring.deviceConfig.service.impl;

import com.example.equipmentmanagementspring.deviceConfig.dao.ModelInformationDao;
import com.example.equipmentmanagementspring.deviceConfig.entity.ModelInformationEntity;
import com.example.equipmentmanagementspring.deviceConfig.service.ModelInformationService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;


@Service("ModelInformationService")
public class ModelInformationServiceImpl extends
        MppServiceImpl<ModelInformationDao, ModelInformationEntity> implements ModelInformationService {
}
