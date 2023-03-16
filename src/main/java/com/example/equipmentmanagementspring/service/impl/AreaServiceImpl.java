package com.example.equipmentmanagementspring.service.impl;

import com.example.equipmentmanagementspring.entity.AreaEntity;
import com.example.equipmentmanagementspring.mapper.AreaDao;
import com.example.equipmentmanagementspring.service.AreaService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

@Service("AreaService")
public class AreaServiceImpl extends
        MppServiceImpl<AreaDao, AreaEntity> implements AreaService {
}
