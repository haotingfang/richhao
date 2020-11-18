package com.example.common.core.domain.entity;

import com.example.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class RoleMenu extends BaseEntity {

    /*菜单id*/
    private Long id;

    /*角色id*/
    private long roleId;

    /*菜单id*/
    private long menuId;
}
