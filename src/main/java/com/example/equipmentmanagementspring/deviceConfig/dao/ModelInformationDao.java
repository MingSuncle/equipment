package com.example.equipmentmanagementspring.deviceConfig.dao;

import com.example.equipmentmanagementspring.deviceConfig.entity.ModelInformationEntity;
import com.example.equipmentmanagementspring.deviceConfig.form.ModelForm;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelInformationDao extends MppBaseMapper<ModelInformationEntity> {

    /**
     *查询全部模型
     */
    List<ModelInformationEntity> getAllModel();

    /***
     * 模型数量
     */
    List<ModelForm> getModelByType();

    Integer getModelNumByType();

    List<ModelInformationEntity> getModelById(String modelId);

    Integer getModelNumById(String modelId);

    Integer saveModelPath(String modelId,String modelVersion,String modelPath);

    ModelInformationEntity selectOne(String modelId,String modelVersion);
}
