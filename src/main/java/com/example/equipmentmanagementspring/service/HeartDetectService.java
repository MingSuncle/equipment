package com.example.equipmentmanagementspring.service;


import com.example.equipmentmanagementspring.entity.HeartDetectEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

public interface HeartDetectService extends IMppService<HeartDetectEntity> {

    HeartDetectEntity getPic(String video_id);


}
