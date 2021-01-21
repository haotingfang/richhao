package com.example.dao.mapper;

import com.example.common.core.domain.entity.SysDictType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典类型表(SysDictType)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-21 14:37:44
 */
public interface SysDictTypeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    SysDictType queryById(Long dictId);

    /**
     * 查询指定行数据
     *
     * @param sysDictType 查询参数
     * @return 对象列表
     */
    List<SysDictType> selectDictTypeList(SysDictType sysDictType);




    /**
     * 新增数据
     *
     * @param sysDictType 实例对象
     * @return 影响行数
     */
    int insert(SysDictType sysDictType);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysDictType> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysDictType> entities);


    /**
     * 修改数据
     *
     * @param sysDictType 实例对象
     * @return 影响行数
     */
    int update(SysDictType sysDictType);

    /**
     * 通过主键删除数据
     *
     * @param dictId 主键
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") Long[] dictIds ,@Param("updateBy") String updateBy);

}