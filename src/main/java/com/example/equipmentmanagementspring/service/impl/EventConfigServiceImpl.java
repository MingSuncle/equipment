package com.example.equipmentmanagementspring.service.impl;

import com.example.equipmentmanagementspring.entity.EventConfigEntity;
import com.example.equipmentmanagementspring.mapper.EventConfigDao;
import com.example.equipmentmanagementspring.service.EventConfigService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("EventConfigService")
public class EventConfigServiceImpl extends
        MppServiceImpl<EventConfigDao, EventConfigEntity> implements EventConfigService {

    @Override
    public void addEventConfig(EventConfigEntity EventConfig){

    }

    @Override
    public void deleteEventConfig(String EventNo,Integer state){
        baseMapper.deleteEventConfig(EventNo,state);
    }

    @Override
    public void updateEventConfig(EventConfigEntity EventConfig){

    }

    @Override
    public List<EventConfigEntity> getEventConfigListByName(String eventName, String ipcId, Integer currentPage, Integer pageSize) {
        return baseMapper.getEventConfigListByName(eventName, ipcId, currentPage, pageSize);
    }

    @Override
    public Integer getEventConfigNumByName(String eventName, String ipcId) {
        return baseMapper.getEventConfigNumByName(eventName, ipcId);
    }

    @Override
    public Integer getEventConfigListNum(String ipcId){
        return baseMapper.getEventConfigListNum(ipcId);
    }

    @Override
    public List<EventConfigEntity> getEventConfigList(String ipcId, Integer currentPage, Integer pageSize) {
        return baseMapper.getEventConfigList(ipcId, currentPage, pageSize);
    }

    @Override
    public EventConfigEntity getEventConfig(String EventId,String ipcId, Integer state) {
        return baseMapper.getEventConfig(EventId,ipcId, state);
    }


}
