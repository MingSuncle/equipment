package com.example.equipmentmanagementspring.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.equipmentmanagementspring.entity.ProcessInformationEntity;
import com.example.equipmentmanagementspring.form.ProcessInformationForm;
import com.example.equipmentmanagementspring.form.UserForm;
import com.example.equipmentmanagementspring.mapper.ProcessInformationDao;
import com.example.equipmentmanagementspring.service.ProcessInformationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("processInformationService")
public class ProcessInformationServiceImpl extends
        ServiceImpl<ProcessInformationDao, ProcessInformationEntity> implements ProcessInformationService {
    @Override
    public List<ProcessInformationForm> queryProcessInformations(){

        return baseMapper.queryProcessInformations();
    };

    @Override
    public List<ProcessInformationForm> queryProcessInformationsByMessageId(Long messageId,Integer currentPage,Integer pageSize){

        return baseMapper.queryProcessInformationsByMessageId(messageId,currentPage,pageSize);
    };

    @Override
    public List<ProcessInformationForm> queryProcessInformationsByUserId(String userId,Integer currentPage,Integer pageSize){

        return baseMapper.queryProcessInformationsByUserId(userId,currentPage,pageSize);
    };

    @Override
    public  List<UserForm> queryUserInformationsByUProId(Long messageId){
        return baseMapper.queryUserInformationsByUProId(messageId);
    }

    @Override
    public ProcessInformationForm lastProcessInformationByMessageId(Long messageId){
        return baseMapper.lastProcessInformationByMessageId(messageId);
    }
}
