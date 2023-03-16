package com.example.equipmentmanagementspring.deviceConfig.service;

import com.example.equipmentmanagementspring.deviceConfig.entity.EventEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

public interface EventService extends IMppService<EventEntity> {

    EventEntity selectByEventId(Integer eventId);
}
