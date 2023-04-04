package com.example.equipmentmanagementspring.deviceConfig.service.impl;

import com.example.equipmentmanagementspring.deviceConfig.dao.ModelInformationDao;
import com.example.equipmentmanagementspring.deviceConfig.entity.ModelInformationEntity;
import com.example.equipmentmanagementspring.deviceConfig.service.ModelInformationService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service("ModelInformationService")
public class ModelInformationServiceImpl extends
        MppServiceImpl<ModelInformationDao, ModelInformationEntity> implements ModelInformationService {
    @Override
    public Path getModelPath(String modelId, String modelVersion) throws NullPointerException{
        ModelInformationEntity model = new ModelInformationEntity();
        model.setModelId(modelId);
        model.setModelVersion(modelVersion);

        try{
            ModelInformationEntity target = baseMapper.selectByMultiId(model);
            Path path = Paths.get(target.getModelFile());
            return path;
        }
        catch (NullPointerException e){

        }
        return null;
    }

    @Override
    public int saveModelPath(String modelId,String modelVersion,String modelPath) {
        return baseMapper.saveModelPath(modelId,modelVersion,modelPath);
    }

    @Override
    public List<ModelInformationEntity> getModelById(String modelId) {
        return baseMapper.getModelById(modelId);
    }

    @Override
    public Integer getModelNumById(String modelId) {
        return baseMapper.getModelNumById(modelId);
    }

    @Override
    public boolean isCreated(String modelId,String versionId) {
        try{
            if(baseMapper.selectOne(modelId,versionId)!=null){
                return true;
            }
            else{
                return false;
            }
        }catch (NullPointerException e){
            return false;
        }

    }

    @Override
    public boolean isModelCreated(String modelId) {
        try{
            if(baseMapper.getModelById(modelId).size()!=0){
                return true;
            }
            else{
                return false;
            }
        }catch (NullPointerException e){
            return false;
        }
    }


}
