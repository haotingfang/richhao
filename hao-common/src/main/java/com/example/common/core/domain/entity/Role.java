package com.example.common.core.domain.entity;

import com.example.common.core.domain.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Role extends BaseEntity {
    /*角色id*/
    private Long id;

    /*角色名称*/
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /*角色编号*/
    @NotBlank(message ="角色编号不能为空")
    private String roleCode;

    /*角色状态（0正常 1停用）*/
    private String status;

    /*删除标志（0代表存在 2代表删除）*/
    private String delFlag;

    /*备注信息*/
    private String remark;

    /*菜单信息*/
    private Long[] menus;

}
