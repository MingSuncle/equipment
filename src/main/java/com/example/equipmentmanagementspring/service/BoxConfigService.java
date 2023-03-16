package com.example.equipmentmanagementspring.service;


import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;

public interface BoxConfigService extends IMppService<BoxConfigEntity> {


    /**
     * 增
     * @return 处理单信息的列表
     */
    void addBoxConfig(BoxConfigEntity boxConfig);

    /**
     * 删
     * @return 处理单信息的列表
     */

    void deleteBoxConfig(String boxNo,Integer state);

    /**
     * 改
     * @return 处理单信息的列表
     */
    void updateBoxConfig(BoxConfigEntity boxConfig);

    /**
     * 获取数量，方便分页
     * @return Box配置列表
     */
    Integer getBoxConfigListNum(String centerId);

    /**
     * 查
     * @return Box配置列表
     */
    List<BoxConfigEntity> getBoxConfigList(String centerId, Integer currentPage, Integer pageSize);

    /**
     * 条件查
     * @return Box配置列表
     */
    List<BoxConfigEntity> getBoxConfigListByName(String boxName,String centerId, Integer currentPage, Integer pageSize);

    /**
     * 条件查数量
     * @return Integer
     */
    Integer getBoxConfigListNumByName(String boxName,String centerId);


    /**
     * 根据状态以及盒子id查询
     * @return Box
     */
    BoxConfigEntity getBoxConfig(String boxId,Integer state);

    List<BoxConfigEntity> getAll();

    Integer allNum();
}
