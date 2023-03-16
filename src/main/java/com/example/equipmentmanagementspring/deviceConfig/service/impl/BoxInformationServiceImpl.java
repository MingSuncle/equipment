package com.example.equipmentmanagementspring.deviceConfig.service.impl;


import com.example.equipmentmanagementspring.deviceConfig.dao.BoxInformationDao;
import com.example.equipmentmanagementspring.deviceConfig.entity.BoxInformationEntity;
import com.example.equipmentmanagementspring.deviceConfig.service.BoxInformationService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

@Service("BoxInformationService")
public class BoxInformationServiceImpl extends
        MppServiceImpl<BoxInformationDao, BoxInformationEntity> implements BoxInformationService {
    /**
     *
     * @param boxId
     * @return
     */
    @Override
    public Integer boxState(String boxId) {
        BoxInformationEntity box = baseMapper.selectById(boxId);
        if (box == null) {
            //盒子不存在返回0
            return 0;
        } else {
            if (box.getBoxState() == 0) {
                //盒子未激活
                return 1;
            } else {
                //其他情况
                return 2;
            }

        }

    }


    @Override
    public Integer activateBox(String boxId) {
        System.out.print(boxState(boxId));
        if (boxState(boxId) == 1) {
            //盒子未激活
            boolean flag = baseMapper.activateBox(boxId);
            //激活成功后返回200
            return 200;
        } else if (boxState(boxId) == 2) {
            //其他情况
            return 201;
        }
        //盒子不在数据库中
        return 404;
    }
}