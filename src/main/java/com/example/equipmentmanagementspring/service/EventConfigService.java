package com.example.equipmentmanagementspring.service;


import com.example.equipmentmanagementspring.entity.EventConfigEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;

public interface EventConfigService extends IMppService<EventConfigEntity> {


    /**
     * 增
     * @return null
     */
    void addEventConfig(EventConfigEntity EventConfig);

    /**
     * 删
     * @return null
     */

    void deleteEventConfig(String EventId,Integer state);

    /**
     * 改
     * @return null
     */
    void updateEventConfig(EventConfigEntity EventConfig);


    /**
     * 条件查
     * @return Event配置列表
     */
    List<EventConfigEntity> getEventConfigListByName(String eventName,String ipcId,Integer currentPage,Integer pageSize);

    /**
     * 查
     * @return Event配置数目
     */
    Integer getEventConfigNumByName(String eventName,String ipcId);

    /**
     * 获取数量，方便分页
     * @return Event配置列表
     */
    Integer getEventConfigListNum(String ipcId);

    /**
     * 查
     * @return Event配置列表
     */
    List<EventConfigEntity> getEventConfigList(String ipcId, Integer currentPage, Integer pageSize);

    /**
     * 根据状态以及盒子id查询
     * @return Event
     */
    EventConfigEntity getEventConfig(String EventId,String ipcId,Integer state);

}
