package com.example.dao.service;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Card;

import java.util.List;

/**
 * (Card)表服务接口
 *
 * @author makejava
 * @since 2021-02-05 15:08:21
 */
public interface CardService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Card queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param pageSize 一页数量
     * @param pageNum  当前页
     * @return 对象列表
     */
    TableDataInfo queryAllByLimit(Card card, Integer pageSize , Integer pageNum);

    /**
     * 新增数据
     *
     * @param card 实例对象
     * @return 实例对象
     */
    AjaxResult insert(Card card);

    /**
     * 修改数据
     *
     * @param card 实例对象
     * @return 实例对象
     */
    AjaxResult update(Card card);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteByIds(Long[] ids);

}