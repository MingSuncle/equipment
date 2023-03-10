package com.example.equipmentmanagementspring.box.service.impl;

import com.example.equipmentmanagementspring.box.dao.ModelInformationDao;
import com.example.equipmentmanagementspring.box.entity.ModelInformationEntity;
import com.example.equipmentmanagementspring.box.service.ModelInformationService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;


@Service("ModelInformationService")
public class ModelInformationServiceImpl extends
        MppServiceImpl<ModelInformationDao, ModelInformationEntity> implements ModelInformationService {
}
