package com.example.equipmentmanagementspring.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("center_config")
public class CenterConfigEntity implements Serializable{

        private static final long serialVersionUID = 1L;

        @MppMultiId
        @TableField(value = "center_id")
        private String centerId;

        @TableField(value = "center_ip")
        private String centerIp;

        @TableField(value = "project_id")
        private String projectId;

        @TableField(value = "center_third_party_urls")
        private String centerThirdPartyUrls;

        @MppMultiId
        @TableField(value = "state")
        private Integer state;
    }
