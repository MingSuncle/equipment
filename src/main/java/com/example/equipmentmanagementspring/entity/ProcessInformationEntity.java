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
 * 消息处理表单
 *
 * @author Zhao Zian
 */
@Data
@TableName("processinfo")
public class ProcessInformationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "process_id" , type = IdType.AUTO)
    private Long processId;

    @TableField(value = "message_id")
    private Integer messageId;

    @TableField(value = "sender_id")
    private String senderId;

    @TableField(value = "send_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    @TableField(value = "process_remark")
    private String processRemark;

    @TableField(value = "process_request")
    private String processRequest;

    @TableField(value = "process_deadline")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date processDeadline;

    @TableField(value = "processor_id")
    private String processorId;


}