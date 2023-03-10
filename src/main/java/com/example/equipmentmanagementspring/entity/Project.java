package com.example.equipmentmanagementspring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Project对象", description = "")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("项目编号")
    @TableId(value = "project_id", type = IdType.ASSIGN_UUID)
    private String projectId;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("项目建设单位")
    private String projectEmployer;

    @ApiModelProperty("项目施工单位")
    private String projectContractor;

    @ApiModelProperty("项目负责人编号")
    private String userId;

    @ApiModelProperty("项目所在地址")
    private String projectAddress;

    @ApiModelProperty("经度")
    private BigDecimal longitude;

    @ApiModelProperty("维度")
    private BigDecimal latitude;
}
