package com.example.equipmentmanagementspring.service.impl;


import com.example.equipmentmanagementspring.entity.HeartDetectEntity;
import com.example.equipmentmanagementspring.mapper.HeartDetectDao;
import com.example.equipmentmanagementspring.service.HeartDetectService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

@Service("HeartDetectService")
public class HeartDetectServiceImpl extends
        MppServiceImpl<HeartDetectDao, HeartDetectEntity> implements HeartDetectService {

    @Override
    public HeartDetectEntity getPic(String video_id) {
        return baseMapper.getPic(video_id);
    }

}
