package com.example.equipmentmanagementspring.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("box_config")
public class BoxConfigEntity implements Serializable{

        private static final long serialVersionUID = 1L;

        @MppMultiId
        @TableField(value = "box_no")
        private String boxNo;

        @TableField(value = "box_name")
        private String boxName;

        @TableField(value = "box_ip")
        private String boxIp;

        @TableField(value = "box_center_id")
        private String boxCenterId;

        @TableField(value = "project_id")
        private String projectId;

        @TableField(value = "center_third_party_urls")
        private String centerThirdPartyUrls;

        @TableField(value = "box_ai_event_id")
        private String boxAiEventId;

        @MppMultiId
        @TableField(value = "state")
        private Integer state;

    }
