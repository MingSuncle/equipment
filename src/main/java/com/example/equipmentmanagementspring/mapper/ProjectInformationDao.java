package com.example.equipmentmanagementspring.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.equipmentmanagementspring.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface ProjectInformationDao extends BaseMapper<Project> {

    /**
     * 根据用户id查询所有处理单信息
     * @return 处理单信息的列表
     */
    List<Project> queryProjectInformationByUserId(String userId);
}
