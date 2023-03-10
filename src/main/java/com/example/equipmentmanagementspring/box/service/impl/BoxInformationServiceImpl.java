package com.example.equipmentmanagementspring.box.service.impl;


import com.example.equipmentmanagementspring.box.dao.BoxInformationDao;
import com.example.equipmentmanagementspring.box.entity.BoxInformationEntity;
import com.example.equipmentmanagementspring.box.service.BoxInformationService;
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
            return 0;
        } else {
            if (box.getBoxState() == 0) {
                return 1;
            } else {
                return 2;
            }

        }

    }


    @Override
    public Integer activateBox(String boxId) {
        System.out.print(boxState(boxId));
        if (boxState(boxId) == 1) {
            boolean flag = baseMapper.activateBox(boxId);
            return 200;
        } else if (boxState(boxId) == 2) {
            return 201;
        }
        return 404;
    }
}