package com.example.equipmentmanagementspring.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.equipmentmanagementspring.entity.ProcessInformationEntity;
import com.example.equipmentmanagementspring.form.ProcessInformationForm;
import com.example.equipmentmanagementspring.form.UserForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProcessInformationDao extends BaseMapper<ProcessInformationEntity> {

    /**
     * 查询所有处理单信息
     * @return 处理单信息的列表
     */
    List<ProcessInformationForm> queryProcessInformations();

    /**
     * 根据报警消息id查询所有处理单信息
     * @return 处理单信息的列表
     */
    List<ProcessInformationForm> queryProcessInformationsByMessageId(Long messageId, Integer currentPage, Integer pageSize);

    /**z
     * 根据用户id查询所有处理单信息
     * @return 处理单信息的列表
     */
    List<ProcessInformationForm> queryProcessInformationsByUserId(String userId, Integer currentPage, Integer pageSize);

    /**
     * 根据项目id查询项目下的用户
     * @return 用户信息的列表
     */
    List<UserForm> queryUserInformationsByUProId(Long messageId);

    /**
     * 获取报警消息下的最新处理单
     * @return 处理单信息
     */
   ProcessInformationForm lastProcessInformationByMessageId(Long messageId);
}
