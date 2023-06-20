package com.example.equipmentmanagementspring.deviceConfig.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("process_param")
public class ProcessParam {
    @TableId("process_param_id")
    private Integer processParamId;

    @TableField("process_param_name")
    private String processParamName;
}
