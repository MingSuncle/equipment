package com.example.equipmentmanagementspring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipmentmanagementspring.entity.ProcessInformationEntity;
import com.example.equipmentmanagementspring.form.ProcessInformationForm;
import com.example.equipmentmanagementspring.form.UserForm;

import java.util.List;

public interface ProcessInformationService extends IService<ProcessInformationEntity> {
    /**
     * 查询所有处理单信息
     * @return 社团信息的列表
     */

    List<ProcessInformationForm> queryProcessInformations();

    /**
     * 根据报警消息id查询所有处理单信息
     * @return 社团信息的列表
     */
    List<ProcessInformationForm> queryProcessInformationsByMessageId(Long messageId,Integer currentPage,Integer pageSize);

    /**
     * 根据用户id查询所有处理单信息
     * @return 社团信息的列表
     */
    List<ProcessInformationForm> queryProcessInformationsByUserId(String userId,Integer currentPage,Integer pageSize);

    /**
     * 根据项目id查询下属用户信息
     * @return 用户信息的列表
     */
    List<UserForm> queryUserInformationsByUProId(Long messageId);

    /**
     * 获取报警消息下的最新处理单
     * @return 处理单信息
     */
    ProcessInformationForm lastProcessInformationByMessageId(Long messageId);
}

