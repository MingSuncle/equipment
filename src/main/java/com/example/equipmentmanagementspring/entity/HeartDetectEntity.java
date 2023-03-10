package com.example.equipmentmanagementspring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("heart_detect")
public class HeartDetectEntity implements Serializable{

        private static final long serialVersionUID = 1L;

        @TableId(value = "id",type= IdType.AUTO)
        private Integer id;

        @TableField(value = "center_id")
        private String centerId;

        @TableField(value = "box_id")
        private String boxId;

        @TableField(value = "video_id")
        private String videoId;

        @TableField(value = "url")
        private String url;

        @TableField(value = "update_time")
        @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
        private Date update_time;
    }
