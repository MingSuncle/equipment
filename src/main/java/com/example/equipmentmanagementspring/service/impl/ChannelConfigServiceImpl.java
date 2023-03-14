package com.example.equipmentmanagementspring.service.impl;


import com.example.equipmentmanagementspring.entity.ChannelEntity;
import com.example.equipmentmanagementspring.mapper.ChannelDao;
import com.example.equipmentmanagementspring.service.ChannelService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

@Service("ChannelConfigService")
public class ChannelConfigServiceImpl extends
        MppServiceImpl<ChannelDao, ChannelEntity> implements ChannelService {
}
