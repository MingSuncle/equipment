package com.example.equipmentmanagementspring.deviceConfig.service;

import com.example.equipmentmanagementspring.deviceConfig.entity.ProcessParam;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;

public interface ProcessParamService extends IMppService<ProcessParam> {

    List<ProcessParam> getAll();
}
