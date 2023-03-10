package com.example.equipmentmanagementspring.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.equipmentmanagementspring.entity.AIInformationEntity;

import java.util.List;

public interface AIInformationService extends IService<AIInformationEntity> {

    /**
     * 获取全部AI信息
     * @return AI消息表单LIST
     */
    List<AIInformationEntity> queryAIInformationList();

    /**
     * 根据项目ID获取AI消息
     * @return AI消息表单LIST
     */
    List<AIInformationEntity> queryAIInformationListByProId(String proId);

    /**
     * 根据项目id和事件状态查询
     * @return 返回AI消息列表
     */
    List<AIInformationEntity> queryUnconfirmedAIInformationList(String usrId,List proId,Integer state,String beginTime,String endTime,List type,Integer currentPage,Integer pageSize);

    /**
     * 条件查询
     * @return 返回AI消息数目
     */
    Integer UnconfirmedAIInformationNum(String usrId,List proId, Integer state, String beginTime, String endTime, List type);

    /**
     * 根据用户ID和事件状态查询AI消息条数
     * @return AI消息条数
     */
    Long AIInformationNumByIdState(String usrId,Integer state);

    /**
     * 根据用户ID和事件状态查询AI消息
     * @return 返回AI消息列表
     */
    List<AIInformationEntity> AIInformationByIdState(String usrId,Integer state,Integer currentPage,Integer pageSize);

    /**
     * 根据用户ID返回待办事件
     * @return AI消息列表
     */
    List<AIInformationEntity> getUnaccomplishedAIInformationByusrId(String usrId);
}