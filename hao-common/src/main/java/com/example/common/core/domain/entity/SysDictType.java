package com.example.common.core.domain.entity;

import com.example.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.ToString;


/**
 * 字典类型表 sys_dict_type
 *
 * @author ruoyi
 */
@Data
@ToString(callSuper = true)
public class SysDictType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 字典主键 */
    private Long dictId;

    /** 字典名称 */
    private String dictName;

    /** 字典类型 */
    private String dictType;

    /** 状态（0正常 1停用） */
    private String status;

    /** 删除状态（0正常 2删除） */
    private String delFlag;

    /** 备注 */
    private String remark;


}
