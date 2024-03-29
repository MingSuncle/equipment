package com.example.equipmentmanagementspring.deviceConfig.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("box_information")
public class BoxInformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "box_id")
    private String boxId;

    @TableField(value = "box_state")
    private Integer boxState;

    @TableField(value = "expire_time")
    private Date expireTime;

    @TableField(value = "channel_number_limit")
    private Integer channelNumberLimit;

    @TableField(value = "AIevent_limit")
    private String AIeventLimit;

    @TableField(value = "activate_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activateTime;

    @TableField(value = "sale_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date saleTime;

}
