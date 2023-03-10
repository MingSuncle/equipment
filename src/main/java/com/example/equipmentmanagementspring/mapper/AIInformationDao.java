package com.example.equipmentmanagementspring.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.equipmentmanagementspring.entity.AIInformationEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AI消息
 *
 * @author Zhao Zian
 */
@Mapper
public interface AIInformationDao extends BaseMapper<AIInformationEntity> {


    /**
     * 查询全部AI消息
     * @return 返回AI消息列表
     */
    List<AIInformationEntity> queryAIInformationList();

    /**
     * 根据项目ID查询AI消息
     * @return 返回消息列表
     */
    List<AIInformationEntity> queryAIInformationListByProId(String proId);

    /**
     * 条件查询
     * @return 返回AI消息列表
     */
    List<AIInformationEntity> queryUnconfirmedAIInformationList(String usrId,List proId, Integer state, String beginTime, String endTime, List type, Integer currentPage, Integer pageSize);

    /**
     * 条件查询
     * @return 返回AI消息数目
     */
    Integer UnconfirmedAIInformationNum(String usrId,List proId, Integer state, String beginTime, String endTime, List type);

    /**
     * 根据用户ID和事件状态查询AI消息
     * @return 返回AI消息列表
     */
    List<AIInformationEntity> AIInformationByIdState(String usrId, Integer state, Integer currentPage, Integer pageSize);

    /**
     * 根据用户ID和事件状态查询AI消息条数
     * @return AI消息条数
     */
    Long AIInformationNumByIdState(String usrId,Integer state);

    /**
     * 根据用户ID返回待办事件
     * @return AI消息列表
     */
    List<AIInformationEntity> getUnaccomplishedAIInformationByusrId(String usrId);
}