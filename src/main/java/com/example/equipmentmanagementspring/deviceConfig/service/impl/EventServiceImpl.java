package com.example.equipmentmanagementspring.deviceConfig.service.impl;


import com.example.equipmentmanagementspring.deviceConfig.dao.EventDao;
import com.example.equipmentmanagementspring.deviceConfig.entity.EventEntity;
import com.example.equipmentmanagementspring.deviceConfig.service.EventService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;

import org.springframework.stereotype.Service;

@Service("EventService")
public class EventServiceImpl extends
        MppServiceImpl<EventDao, EventEntity> implements EventService {


    @Override
    public EventEntity selectByEventId(Integer eventId){
        return baseMapper.selectByEventId(eventId);
    }
}
