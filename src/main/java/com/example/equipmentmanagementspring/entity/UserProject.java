package com.example.equipmentmanagementspring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user_project")
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserProject对象", description = "")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户编号")
    private String userId;

    @ApiModelProperty("项目编号")
    private String projectId;
}
