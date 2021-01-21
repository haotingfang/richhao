package com.example.dao.service;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.SysDictData;
import com.example.common.core.domain.entity.SysDictType;

import java.util.List;

/**
 * 字典数据表(SysDictData)表服务接口
 *
 * @author makejava
 * @since 2021-01-21 14:39:40
 */
public interface SysDictDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param dictCode 主键
     * @return 实例对象3
     */
    AjaxResult queryById(Long dictCode);

    /**
     * 查询字典数据列表
     *
     * @param sysDictData
     * @param pageSize
     * @param pageNum
     * @return TableDataInfo
     */
    TableDataInfo selectDictDataList(SysDictData sysDictData, Integer pageSize , Integer pageNum);


    /**
     * 字典数据删除 真删
     *
     * @param sysDictDataIds id集合
     * @return int
     */
    int delete(Long[] sysDictDataIds);

    /**
     * 新增字典类型列表
     *
     * @param sysDictData
     * @return AjaxResult
     */
    AjaxResult addDictData(SysDictData sysDictData);

    /**
     * 更新字典类型列表
     *
     * @param sysDictData
     * @return AjaxResult
     */
    AjaxResult editDictData(SysDictData sysDictData);


}