package com.example.equipmentmanagementspring.mapper;



import com.example.equipmentmanagementspring.entity.HeartDetectEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;


public interface HeartDetectDao extends MppBaseMapper<HeartDetectEntity> {
    HeartDetectEntity getPic(String video_id);

    Date getLastDate(String boxId);
}
