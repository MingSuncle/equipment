package com.example.equipmentmanagementspring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyw
 * @since 2022-04-05
 */
@TableName("center_config")
@Data
@Builder
public class CenterConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "center_id", type = IdType.ASSIGN_UUID)
    private String centerId;

    private String centerIp;

    private String projectId;

    private String centerThirdPartyUrls;

    private Integer centerState;


    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getCenterIp() {
        return centerIp;
    }

    public void setCenterIp(String centerIp) {
        this.centerIp = centerIp;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCenterThirdPartyUrls() {
        return centerThirdPartyUrls;
    }

    public void setCenterThirdPartyUrls(String centerThirdPartyUrls) {
        this.centerThirdPartyUrls = centerThirdPartyUrls;
    }

    public Integer getCenterState() {
        return centerState;
    }

    public void setCenterState(Integer centerState) {
        this.centerState = centerState;
    }

    @Override
    public String toString() {
        return "CenterConfig{" +
        "centerId=" + centerId +
        ", centerIp=" + centerIp +
        ", projectId=" + projectId +
        ", centerThirdPartyUrls=" + centerThirdPartyUrls +
        ", centerState=" + centerState +
        "}";
    }
}
