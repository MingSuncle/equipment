package com.example.equipmentmanagementspring.form;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@ApiModel(value = "事件表单", description = "事件详情")
public class ProcessInformationForm {


    @Null
    private Long processId;


    @NotNull(message = "消息id不能为空")
    private Integer messageId;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "抄送时间不为空")
    private String sendTime;


    private String processRemark;


    @NotNull
    private String processorId;


    @NotNull
    private String senderId;

    private String processRequest;


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String processDeadline;

    private String processor;

    private String sender;
}
