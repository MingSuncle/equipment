package com.example.equipmentmanagementspring.mapper;


import com.example.equipmentmanagementspring.entity.ChannelEntity;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.apache.ibatis.annotations.Mapper;



@Mapper
@EnableMPP
public interface ChannelDao extends MppBaseMapper<ChannelEntity> {
}
