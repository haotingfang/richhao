package com.example.dao.mapper;


import com.example.common.core.domain.entity.RelationUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户和角色关联表(RelationUserRole)表数据库访问层
 *
 * @author hao
 * @since 2020-12-11 16:52:04
 */
public interface RelationUserRoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    RelationUserRole queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<RelationUserRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param relationUserRole 实例对象
     * @return 对象列表
     */
    List<RelationUserRole> queryAll(RelationUserRole relationUserRole);

    /**
     * 新增数据
     *
     * @param relationUserRole 实例对象
     * @return 影响行数
     */
    int insert(RelationUserRole relationUserRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<RelationUserRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<RelationUserRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<RelationUserRole> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<RelationUserRole> entities);

    /**
     * 修改数据
     *
     * @param relationUserRole 实例对象
     * @return 影响行数
     */
    int update(RelationUserRole relationUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);


    int countUserRoleByRoleId(@Param("roleId") Long roleId);

}
