package com.example.dao.mapper;

import com.example.common.core.domain.entity.Card;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Card)表数据库访问层
 *
 * @author makejava
 * @since 2021-02-02 17:54:06
 */
public interface CardMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Card queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Card> queryAllByLimit(Card card);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param card 实例对象
     * @return 对象列表
     */
    List<Card> queryAll(Card card);

    /**
     * 新增数据
     *
     * @param card 实例对象
     * @return 影响行数
     */
    int insert(Card card);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Card> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Card> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Card> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Card> entities);

    /**
     * 修改数据
     *
     * @param card 实例对象
     * @return 影响行数
     */
    int update(Card card);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteByIds(Long[] ids);

}