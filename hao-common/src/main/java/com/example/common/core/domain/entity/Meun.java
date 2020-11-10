package com.example.common.core.domain.entity;

import com.example.common.core.domain.BaseEntity;
import lombok.Data;

@Data
public class Meun extends BaseEntity {
    /* id                bigint(20)      not null auto_increment    comment '菜单ID',
  menu_name         varchar(50)     not null                   comment '菜单名称',
  parent_id         bigint(20)      default 0                  comment '父菜单ID',
  order_num         int(4)          default 0                  comment '显示顺序',
  path              varchar(200)    default ''                 comment '路由地址',
  component         varchar(255)    default null               comment '组件路径',
  menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  status            char(1)         default 0                  comment '菜单状态（0正常 1停用）',
  perms             varchar(100)    default null               comment '权限标识',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default ''                 comment '备注',*/

    /*菜单id*/
    private Long id;

    /*菜单名称*/
    private String menuName;

    /*父菜单ID*/
    private Long parentId;

    /*显示顺序*/
    private Integer orderNum;

    /*路由地址*/
    private String path;

    /*组件路径*/
    private Long component;

    /*菜单类型（M目录 C菜单 F按钮）*/
    private String menuType;

    /*菜单状态（0正常 1停用）*/
    private String status;

    /*权限标识*/
    private String perms;

    /*备注*/
    private String remark;
}
