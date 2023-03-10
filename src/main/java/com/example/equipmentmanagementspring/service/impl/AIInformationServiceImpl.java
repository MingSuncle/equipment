package com.example.equipmentmanagementspring.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipmentmanagementspring.entity.AIInformationEntity;
import com.example.equipmentmanagementspring.mapper.AIInformationDao;
import com.example.equipmentmanagementspring.service.AIInformationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AIInformationService")
public class AIInformationServiceImpl extends
        ServiceImpl<AIInformationDao, AIInformationEntity> implements AIInformationService {
    @Override
    public List<AIInformationEntity> queryAIInformationList(){
        return baseMapper.queryAIInformationList();
    }

    @Override
    public List<AIInformationEntity> queryAIInformationListByProId(String proId) {
        return baseMapper.queryAIInformationListByProId(proId);
    }

    @Override

    public List<AIInformationEntity> queryUnconfirmedAIInformationList(String usrId,List proId,Integer state,String beginTime,String endTime,List type,Integer currentPage,Integer pageSize){
        return baseMapper.queryUnconfirmedAIInformationList(usrId,proId,state,beginTime,endTime,type,currentPage,pageSize);}

    @Override
    public Integer UnconfirmedAIInformationNum(String usrId, List proId, Integer state, String beginTime, String endTime, List type) {
        return baseMapper.UnconfirmedAIInformationNum(usrId, proId, state, beginTime, endTime, type);
    }

    ;

    @Override
    public Long AIInformationNumByIdState(String usrId,Integer state){
        return baseMapper.AIInformationNumByIdState(usrId,state);
    };

    @Override
    public List<AIInformationEntity> AIInformationByIdState(String usrId,Integer state,Integer currentPage,Integer pageSize){
        return baseMapper.AIInformationByIdState(usrId,state,currentPage,pageSize);
    }

    @Override
    public List<AIInformationEntity> getUnaccomplishedAIInformationByusrId(String usrId) {
        return baseMapper.getUnaccomplishedAIInformationByusrId(usrId);
    }
}
