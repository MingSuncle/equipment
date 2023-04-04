package com.example.equipmentmanagementspring.mapper;


import com.example.equipmentmanagementspring.entity.CenterConfigEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@EnableMPP
public interface CenterConfigDao extends MppBaseMapper<CenterConfigEntity> {

    /**
     * 增
     * @return
     */
    void addCenterConfig(CenterConfigEntity centerConfig);

    /**
     * 删
     * @return
     */

    void deleteCenterConfig(String centerId,Integer state);

    /**
     * 改
     * @return
     */
     void updateCenterConfig(CenterConfigEntity centerConfig);

    /**
     * 查
     * @return Center配置列表
     */
     List<CenterConfigEntity> getCenterConfigList(String usrId, Integer currentPage, Integer pageSize);

    /**
     * 获取数量，方便分页
     * @return Center配置列表
     */
     Integer getCenterConfigListNum(String usrId);

    /**
     * 根据状态以及节点id查询
     * @return Center
     */
     CenterConfigEntity getCenterConfig(String centerId,Integer state);

    /**
     * 为了配置拉取而写的方法
     * @return CenterList
     */
    List<CenterConfigEntity> SelectCenterConfig(String centerId);
}
