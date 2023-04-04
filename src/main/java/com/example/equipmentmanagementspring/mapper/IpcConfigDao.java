package com.example.equipmentmanagementspring.mapper;


import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.entity.IpcConfigEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface IpcConfigDao extends MppBaseMapper<IpcConfigEntity> {

    /**
     * 增
     * @return
     */
    void addIpcConfig(IpcConfigEntity IpcConfig);

    /**
     * 删
     * @return
     */

    void deleteIpcConfig(String IpcNo,Integer state);

    /**
     * 改
     * @return
     */
     void updateIpcConfig(IpcConfigEntity IpcConfig);

    /**
     * 查
     * @return Ipc配置列表
     */
     List<IpcConfigEntity> getIpcConfigList(String ipcBoxNo,Integer currentPage,Integer pageSize);

    /**
     * 条件查
     * @return Ipc配置列表
     */
    List<IpcConfigEntity> getIpcConfigListByName(String ipcBoxNo,String ipcName,Integer currentPage,Integer pageSize);

    /**
     * 条件查数量
     * @return Integer
     */
    Integer getIpcConfigListNumByName(String ipcBoxNo,String ipcName);


    /**
     * 获取数量，方便分页
     * @return Ipc配置列表
     */
     Integer getIpcConfigListNum(String ipcBoxNo);

    /**
     * 根据状态以及盒子id查询
     * @return Ipc
     */
     IpcConfigEntity getIpcConfig(String IpcId,Integer state);

     /**
     * 为了配置拉取而写的方法
     * @return BoxList
     */
    List<IpcConfigEntity> SelectIpcConfig(String IpcId);

    /**
     * 为了配置拉取而写的方法
     * @return StringList
     */
    List<String> SelectIpcId(String boxNo);

}
