package com.example.equipmentmanagementspring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipmentmanagementspring.entity.Project;

import java.util.List;

public interface ProjectInformationService extends IService<Project> {
    /**
     * 根据用户id查询所有处理单信息
     * @return 处理单信息的列表
     */
    List<Project> queryProjectInformationByUserId(String userId);
}
