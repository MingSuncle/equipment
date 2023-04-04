package com.example.equipmentmanagementspring.deviceConfig.service;

import com.example.equipmentmanagementspring.deviceConfig.entity.ModelInformationEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.nio.file.Path;
import java.util.List;

public interface ModelInformationService extends IMppService<ModelInformationEntity> {
    /**
     * 获取模型文件的位置 目前只写单文件
     */
    Path getModelPath(String modelId, String modelVersion);

    int saveModelPath(String modelId,String modelVersion,String modelPath);

    List<ModelInformationEntity> getModelById(String modelId);

    Integer getModelNumById(String modelId);

    boolean isCreated(String modelId,String versionId);

    boolean isModelCreated(String modelId);
}
