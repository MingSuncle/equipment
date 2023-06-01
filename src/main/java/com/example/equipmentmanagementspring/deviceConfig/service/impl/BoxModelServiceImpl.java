package com.example.equipmentmanagementspring.deviceConfig.service.impl;


import com.example.equipmentmanagementspring.deviceConfig.dao.BoxModelDao;
import com.example.equipmentmanagementspring.deviceConfig.entity.BoxModelEntity;
import com.example.equipmentmanagementspring.deviceConfig.service.BoxModelService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

@Service("BoxModelService")
public class BoxModelServiceImpl extends
        MppServiceImpl<BoxModelDao, BoxModelEntity> implements BoxModelService {

    @Override
    public BoxModelEntity getBoxModel(String boxId, String modelId) {
        BoxModelEntity temp = new BoxModelEntity();
        temp.setBoxId(boxId);
        temp.setModelId(modelId);
        return baseMapper.selectByMultiId(temp);
    }

    @Override
    public BoxModelEntity getOne(String boxId) {
        return baseMapper.getOne(boxId);
    }

    @Override
    public Integer confirmBoxModelVersion(String boxId, String currentVersion) {
        return baseMapper.confirmBoxModelVersion(boxId, currentVersion);
    }
}
