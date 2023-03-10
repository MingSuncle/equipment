package com.example.equipmentmanagementspring.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipmentmanagementspring.entity.Project;
import com.example.equipmentmanagementspring.mapper.ProjectInformationDao;
import com.example.equipmentmanagementspring.service.ProjectInformationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProjectInformationService")
public class ProjectInformationServiceImpl extends
        ServiceImpl<ProjectInformationDao, Project> implements ProjectInformationService {
    @Override
    public List<Project> queryProjectInformationByUserId(String userId){
        return baseMapper.queryProjectInformationByUserId(userId);
    }
}
