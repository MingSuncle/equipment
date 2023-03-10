package com.example.equipmentmanagementspring.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyw
 * @since 2022-04-06
 */
@TableName("ai_event_config")
@ApiModel(value = "AiEventConfig对象", description = "")
public class AiEventConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private String ipcId;

    private String aieventId;

    private String aieventName;

    @ApiModelProperty("置信值")
    private Double confidence;

    @ApiModelProperty("交并比")
    private Double iou;

    @ApiModelProperty("0忽略1一般")
    private Integer leve;


    public String getIpcId() {
        return ipcId;
    }

    public void setIpcId(String ipcId) {
        this.ipcId = ipcId;
    }

    public String getAieventId() {
        return aieventId;
    }

    public void setAieventId(String aieventId) {
        this.aieventId = aieventId;
    }

    public String getAieventName() {
        return aieventName;
    }

    public void setAieventName(String aieventName) {
        this.aieventName = aieventName;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public Double getIou() {
        return iou;
    }

    public void setIou(Double iou) {
        this.iou = iou;
    }

    public Integer getLeve() {
        return leve;
    }

    public void setLeve(Integer leve) {
        this.leve = leve;
    }

    @Override
    public String toString() {
        return "AiEventConfig{" +
        "ipcId=" + ipcId +
        ", aieventId=" + aieventId +
        ", aieventName=" + aieventName +
        ", confidence=" + confidence +
        ", iou=" + iou +
        ", leve=" + leve +
        "}";
    }
}
