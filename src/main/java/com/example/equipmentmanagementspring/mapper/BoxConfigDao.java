package com.example.equipmentmanagementspring.mapper;

import com.example.equipmentmanagementspring.entity.BoxConfigEntity;
import com.example.equipmentmanagementspring.entity.CenterConfigEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoxConfigDao extends MppBaseMapper<BoxConfigEntity> {

    /**
     * 增
     * @return
     */
    void addBoxConfig(BoxConfigEntity boxConfig);

    /**
     * 删
     * @return
     */

    void deleteBoxConfig(String boxNo,Integer state);

    /**
     * 改
     * @return
     */
     void updateBoxConfig(BoxConfigEntity boxConfig);

    /**
     * 查
     * @return Box配置列表
     */
     List<BoxConfigEntity> getBoxConfigList(String centerId,Integer currentPage,Integer pageSize);


    /**
     * 条件查
     * @return Box配置列表
     */
    List<BoxConfigEntity> getBoxConfigListByName(String boxName,String centerId,Integer currentPage,Integer pageSize);

    /**
     * 条件查
     * @return Box配置列表
     */
    Integer getBoxConfigListNumByName(String boxName,String centerId);


    /**
     * 获取数量，方便分页
     * @return Box配置列表
     */
     Integer getBoxConfigListNum(String centerId);

    /**
     * 根据状态以及盒子id查询
     * @return Box
     */
     BoxConfigEntity getBoxConfig(String boxId,Integer state);

    /**
     * 为了配置拉取而写的方法
     * @return BoxList
     */
    List<BoxConfigEntity> SelectBoxConfig(String boxNo);
}
