package com.example.equipmentmanagementspring.deviceConfig.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("model_information")
public class ModelInformationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @MppMultiId
    @TableField(value = "model_id")
    private String modelId;

    @MppMultiId
    @TableField(value = "model_version")
    private String modelVersion;

    @TableField("model_name")
    private String modelName;

    @TableField("model_file")
    private String modelFile;

    @TableField("model_remark")
    private String modelRemark;

    @TableField(value = "model_update_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modelUpdateTime;
}
