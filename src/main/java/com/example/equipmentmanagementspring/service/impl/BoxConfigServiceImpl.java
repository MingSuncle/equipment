package com.example.equipmentmanagementspring.service.impl;


import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.mapper.BoxConfigDao;
import com.example.equipmentmanagementspring.service.BoxConfigService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoxConfigService")
public class BoxConfigServiceImpl extends
        MppServiceImpl<BoxConfigDao, BoxConfigEntity> implements BoxConfigService {

    @Override
    public void addBoxConfig(BoxConfigEntity boxConfig){

    }

    @Override
    public void deleteBoxConfig(String boxNo,Integer state){
        baseMapper.deleteBoxConfig(boxNo,state);
    }

    @Override
    public void updateBoxConfig(BoxConfigEntity boxConfig){

    }

    @Override
    public Integer getBoxConfigListNum(String centerId){
        return baseMapper.getBoxConfigListNum(centerId);
    }

    @Override
    public List<BoxConfigEntity> getBoxConfigList(String centerId,Integer currentPage, Integer pageSize) {
        return baseMapper.getBoxConfigList(centerId, currentPage, pageSize);
    }

    @Override
    public List<BoxConfigEntity> getBoxConfigListByName(String boxName,String centerId,Integer currentPage, Integer pageSize) {
        return baseMapper.getBoxConfigListByName(boxName,centerId, currentPage, pageSize);
    }

    @Override
    public Integer getBoxConfigListNumByName(String boxName,String centerId) {
        return baseMapper.getBoxConfigListNumByName(boxName,centerId);
    }


    @Override
    public BoxConfigEntity getBoxConfig(String boxId, Integer state) {
        return baseMapper.getBoxConfig(boxId, state);
    }

    @Override
    public List<BoxConfigEntity> getAll() {
        return baseMapper.getAll();
    }

    @Override
    public Integer allNum() {
        return baseMapper.allNum();
    }


}
