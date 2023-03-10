package com.example.equipmentmanagementspring.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表单
 *
 * @author Zhao Zian
 */
@Data
@TableName("aieventmessage")
public class AIInformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "message_id" , type = IdType.AUTO)
    private Long messageId;

    @TableField(value = "project_id")
    private String projectId;

    @TableField(value = "center_ip")
    private String centerIp;

    @TableField(value = "box_ip")
    private String boxIp;

    @TableField(value = "ipc_ip")
    private String ipcIp;


    @TableField(value = "AIevent_name")
    private String AIeventName;

    @TableField(value = "description")
    private String description;


    @TableField(value = "file")
    private String file;

    @TableField(value = "file_type")
    private Integer fileType;

    @TableField(value = "time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;


    @TableField(value = "state")
    private String state;

    @TableField(value = "level")
    private String level;

//    @TableField(value = "method")
//    private String method;
//
//    @TableField(value = "paramet")
//    private String paramet;
//
//    @TableField(value = "AIevent_id")
//    private String AIeventID;


}
