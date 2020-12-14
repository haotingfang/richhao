package com.example.common.core.domain.entity;

import com.example.common.core.domain.BaseEntity;
import lombok.Data;


/**
 * 用户和角色关联表(RelationUserRole)实体类
 *
 * @author hao
 * @since 2020-12-11 16:47:32
 */
@Data
public class RelationUserRole extends BaseEntity {
    private static final long serialVersionUID = 357426633969505180L;
    /**
     * id
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;


}
