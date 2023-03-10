package com.example.equipmentmanagementspring.service.impl;


import com.example.equipmentmanagementspring.entity.CenterConfigEntity;
import com.example.equipmentmanagementspring.mapper.CenterConfigDao;
import com.example.equipmentmanagementspring.service.CenterConfigService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CenterConfigService")
public class CenterConfigServiceImpl extends
        MppServiceImpl<CenterConfigDao, CenterConfigEntity> implements CenterConfigService {

    @Override
    public void addCenterConfig(CenterConfigEntity CenterConfig){

    }

    @Override
    public void deleteCenterConfig(String CenterNo,Integer state){
        baseMapper.deleteCenterConfig(CenterNo,state);
    }

    @Override
    public void updateCenterConfig(CenterConfigEntity CenterConfig){

    }

    @Override
    public Integer getCenterConfigListNum(String usrId){
        return baseMapper.getCenterConfigListNum(usrId);
    }

    @Override
    public List<CenterConfigEntity> getCenterConfigList(String usrId, Integer currentPage, Integer pageSize) {
        return baseMapper.getCenterConfigList(usrId, currentPage, pageSize);
    }

    @Override
    public CenterConfigEntity getCenterConfig(String CenterId, Integer state) {
        return baseMapper.getCenterConfig(CenterId, state);
    }


}
