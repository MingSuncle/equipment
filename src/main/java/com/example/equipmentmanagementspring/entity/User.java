package com.example.equipmentmanagementspring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ls
 * @since 2022-04-05
 */
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "User对象", description = "")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String userId;

    @ApiModelProperty("姓名")
    private String name;

    private String sex;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("电话")
    private String phoneNumber;

    @ApiModelProperty("部门")
    private String department;

    @ApiModelProperty("岗位")
    private String post;

    @ApiModelProperty("角色表id")
    private Integer roleId;

    @ApiModelProperty("0 无效 1有效")
    private Integer userState;
}
