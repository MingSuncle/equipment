package com.example.equipmentmanagementspring.deviceConfig.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@TableName("area")
public class AreaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @MppMultiId
    @TableField(value = "area_id")
    private Integer areaId;

    @MppMultiId
    @TableField(value = "ipc_id")
    private String ipcId;

    @MppMultiId
    @TableField(value = "box_id")
    private String boxId;

    @MppMultiId
    @TableField(value = "channel_id")
    private Integer channelId;

    @TableField(value = "area_name")
    private String areaName;

    @TableField(value = "AIevent_level")
    private Integer AIeventLevel;

    @TableField(value = "AIevent_processmode")
    private Integer AIeventProcessmode;

    @TableField(value = "AIevent_processparam")
    private String AIeventProcessparam;

    @TableField(value = "detect_time_list")
    private String detectTimeList;

    @TableField(value = "detect_area_scope")
    private String detectAreaScope;

    public AreaEntity(Integer areaId, String ipcId, String boxId, Integer channelId){
        this.areaId=areaId;
        this.ipcId=ipcId;
        this.boxId=boxId;
        this.channelId=channelId;
    }
}
