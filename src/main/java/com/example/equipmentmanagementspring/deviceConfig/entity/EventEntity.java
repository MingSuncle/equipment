package com.example.equipmentmanagementspring.deviceConfig.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("aievent")
public class EventEntity {

    @TableId("AIevent_id")
    private Integer AIeventId;

    @TableField("AIevent_name")
    private String AIeventName;

    @TableField("AIevent_description")
    private String AIeventDescription;
}
