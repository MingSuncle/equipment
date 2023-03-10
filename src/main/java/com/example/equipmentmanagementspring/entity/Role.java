package com.example.equipmentmanagementspring.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

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
@ApiModel(value = "Role对象", description = "")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "role_id")
    private Integer roleId;

    @ApiModelProperty("名称")
    private String roleName;

    @ApiModelProperty("描述	0超级管理员：ALL，管理高级管理员	1高级管理员：关联项目查看，管理项目负责人	2项目负责人：关联项目查看，管理设备管理员	3设备管理员：设备配置	4安全员：关联项目AI消息查看+处理	")
    private String roleDescription;
}
