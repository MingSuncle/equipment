package com.example.equipmentmanagementspring.service;


import com.example.equipmentmanagementspring.entity.CenterConfigEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;

public interface CenterConfigService extends IMppService<CenterConfigEntity> {


    /**
     * 增
     * @return 处理单信息的列表
     */
    void addCenterConfig(CenterConfigEntity centerConfig);

    /**
     * 删
     * @return 处理单信息的列表
     */

    void deleteCenterConfig(String centerNo,Integer state);

    /**
     * 改
     * @return 处理单信息的列表
     */
    void updateCenterConfig(CenterConfigEntity centerConfig);

    /**
     * 获取数量，方便分页
     * @return Center配置列表
     */
    Integer getCenterConfigListNum(String usrId);

    /**
     * 查
     * @return Center配置列表
     */
    List<CenterConfigEntity> getCenterConfigList(String usrId, Integer currentPage, Integer pageSize);

    /**
     * 根据状态以及盒子id查询
     * @return Center
     */
    CenterConfigEntity getCenterConfig(String centerId,Integer state);

}
