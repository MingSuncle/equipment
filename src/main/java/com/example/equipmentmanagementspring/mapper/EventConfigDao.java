package com.example.equipmentmanagementspring.mapper;



import com.example.equipmentmanagementspring.entity.EventConfigEntity;
import com.example.equipmentmanagementspring.entity.IpcConfigEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface EventConfigDao extends MppBaseMapper<EventConfigEntity> {

    /**
     * 增
     * @return
     */
    void addEventConfig(EventConfigEntity EventConfig);

    /**
     * 删
     * @return
     */

    void deleteEventConfig(String eventId,Integer state);

    /**
     * 改
     * @return
     */
     void updateEventConfig(EventConfigEntity EventConfig);

    /**
     * 查
     * @return Event配置列表
     */
     List<EventConfigEntity> getEventConfigList(String ipcId,Integer currentPage,Integer pageSize);

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
     * 根据状态以及eventid查询
     * @return Event
     */
     EventConfigEntity getEventConfig(String eventId,String ipcId,Integer state);

    /**
     * 为了配置拉取而写的方法
     * @return BoxList
     */
    List<EventConfigEntity> SelectEventConfig(String eventId,String ipcId);

    /**
     * 为了配置拉取而写的方法
     * @return StringList
     */
    List<String> SelectEventId(String ipcId);
}
