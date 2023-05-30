package com.example.equipmentmanagementspring.deviceConfig.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("box_model")
public class BoxModelEntity {
    @TableId("box_id")
    private String boxId;

    @TableField("model_id")
    private String modelId;

    @TableField("current_version")
    private String currentVersion;

    @TableField("correct_version")
    private String correctVersion;
}
