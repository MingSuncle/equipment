package com.example.equipmentmanagementspring.deviceConfig.service.impl;


import com.example.equipmentmanagementspring.deviceConfig.dao.ProcessParamDao;
import com.example.equipmentmanagementspring.deviceConfig.entity.ProcessParam;
import com.example.equipmentmanagementspring.deviceConfig.service.ProcessParamService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProcessParamService")
public class ProcessParamServiceImpl extends
        MppServiceImpl<ProcessParamDao, ProcessParam> implements ProcessParamService {

    @Override
    public List<ProcessParam> getAll() {
        return baseMapper.getAll();
    }
}
