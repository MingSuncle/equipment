package com.example.equipmentmanagementspring.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("ai_event_config")
public class EventConfigEntity implements Serializable{

        private static final long serialVersionUID = 1L;


        @MppMultiId
        @TableField(value = "ipc_id")
        private String ipcId;

        @MppMultiId
        @TableField(value = "ai_event_id")
        private String aiEventId;

        @TableField(value = "ai_event_name")
        private String aiEventName;

        @TableField(value = "confidence")
        private Double confidence;

        @TableField(value = "iou")
        private Double iou;

        @TableField(value = "level")
        private Integer level;

        @TableField(value = "method")
        private Integer method;

        @TableField(value = "parameter")
        private String parameter;

        @MppMultiId
        @TableField(value = "state")
        private Integer state;
    }
