package com.example.equipmentmanagementspring.service;

import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.entity.IpcConfigEntity;
import com.github.jeffreyning.mybatisplus.service.IMppService;

import java.util.List;

public interface IpcConfigService extends IMppService<IpcConfigEntity> {


    /**
     * 增
     * @return 处理单信息的列表
     */
    void addIpcConfig(IpcConfigEntity IpcConfig);

    /**
     * 删
     * @return 处理单信息的列表
     */

    void deleteIpcConfig(String IpcNo,Integer state);

    /**
     * 改
     * @return 处理单信息的列表
     */
    void updateIpcConfig(IpcConfigEntity IpcConfig);

    /**
     * 获取数量，方便分页
     * @return Ipc配置列表
     */
    Integer getIpcConfigListNum(String usrId);

    /**
     * 查
     * @return Ipc配置列表
     */
    List<IpcConfigEntity> getIpcConfigList(String ipcBoxNo, Integer currentPage, Integer pageSize);

    /**
     * 根据状态以及盒子id查询
     * @return Ipc
     */
    IpcConfigEntity getIpcConfig(String ipcBoxNo,Integer state);

    /**
     * 条件查
     * @return Box配置列表
     */
    List<IpcConfigEntity> getIpcConfigListByName(String ipcName, String boxId, Integer currentPage, Integer pageSize);

    /**
     * 条件查数量
     * @return Integer
     */
    Integer getIpcConfigListNumByName(String ipcName,String boxId);

}
