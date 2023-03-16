package com.example.equipmentmanagementspring.deviceConfig.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;

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
}
