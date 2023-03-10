package com.example.equipmentmanagementspring.service.impl;


import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.entity.IpcConfigEntity;
import com.example.equipmentmanagementspring.mapper.IpcConfigDao;
import com.example.equipmentmanagementspring.service.IpcConfigService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IpcConfigService")
public class IpcConfigServiceImpl extends
        MppServiceImpl<IpcConfigDao, IpcConfigEntity> implements IpcConfigService {

    @Override
    public void addIpcConfig(IpcConfigEntity IpcConfig){

    }

    @Override
    public void deleteIpcConfig(String IpcNo,Integer state){
        baseMapper.deleteIpcConfig(IpcNo,state);
    }

    @Override
    public void updateIpcConfig(IpcConfigEntity IpcConfig){

    }

    @Override
    public Integer getIpcConfigListNum(String usrId){
        return baseMapper.getIpcConfigListNum(usrId);
    }

    @Override
    public List<IpcConfigEntity> getIpcConfigList(String ipcBoxNo, Integer currentPage, Integer pageSize) {
        return baseMapper.getIpcConfigList(ipcBoxNo, currentPage, pageSize);
    }

    @Override
    public IpcConfigEntity getIpcConfig(String IpcId, Integer state) {
        return baseMapper.getIpcConfig(IpcId, state);
    }

    @Override
    public Integer getIpcConfigListNumByName(String ipcName,String boxId) {
        return baseMapper.getIpcConfigListNumByName(boxId,ipcName);
    }


    @Override
    public List<IpcConfigEntity> getIpcConfigListByName(String ipcBoxNo,String ipcName,Integer currentPage,Integer pageSize) {
        return baseMapper.getIpcConfigListByName(ipcName, ipcBoxNo, currentPage, pageSize);
    }

}
