package com.example.equipmentmanagementspring.deviceConfig.dao;


import com.example.equipmentmanagementspring.deviceConfig.entity.EventEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventDao extends MppBaseMapper<EventEntity> {
    EventEntity selectByEventId(Integer eventId);
}
