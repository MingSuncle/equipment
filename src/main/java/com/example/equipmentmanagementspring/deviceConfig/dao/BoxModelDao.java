package com.example.equipmentmanagementspring.deviceConfig.dao;

import com.example.equipmentmanagementspring.deviceConfig.entity.BoxModelEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
@EnableMPP
public interface BoxModelDao extends MppBaseMapper<BoxModelEntity> {

    /***
     * 过渡借口，只考虑每个盒子仅存在一个模型
     * @param boxId
     * @return
     */
    BoxModelEntity getOne(String boxId);

    /***
     * 更新盒子目前模型版本
     * @param boxId
     * @param currentVersion
     * @return Integer
     */
    Integer confirmBoxModelVersion(@Param(value = "boxId")String boxId,@Param(value = "currentVersion")String currentVersion);

    /***
     * 盒子下载代码后更新其状态,设置为已更新
     * @param boxId
     * @return
     */
    Integer setCodeVersion(@Param(value = "boxId")String boxId);

    /***
     * 设置所有列为未更新，用于上传新代码版本后
     * @return
     */
    Integer setCodeVersionUnfinished();
}
