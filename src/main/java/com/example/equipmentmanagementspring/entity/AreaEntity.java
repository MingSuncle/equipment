package com.example.equipmentmanagementspring.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("area")
public class AreaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @MppMultiId
    @TableField(value = "area_id")
    private Integer areaId;

    @TableField(value = "ipc_id")
    private String ipcId;

    @TableField(value = "box_id")
    private String boxId;

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


}
