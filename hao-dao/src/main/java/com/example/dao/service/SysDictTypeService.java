package com.example.dao.service;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.SysDictData;
import com.example.common.core.domain.entity.SysDictType;

import java.util.List;

/**
 * 字典类型表(SysDictType)表服务接口
 *
 * @author makejava
 * @since 2021-01-21 15:41:03
 */
public interface SysDictTypeService {

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    public List<SysDictData> selectDictDataByType(String dictType);


    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    AjaxResult queryById(Long dictId);

    /**
     * 查询字典类型列表
     *
     * @param sysDictType 查询参数
     * @param pageSize
     * @param pageNum
     * @return TableDataInfo
     */
    TableDataInfo selectDictTypeList(SysDictType sysDictType, Integer pageSize , Integer pageNum);


    /**
     * 查询字典类型列表
     *
     * @param sysDictTypeIds id集合
     * @return int
     */
    int delete(Long[] sysDictTypeIds);

    /**
     * 新增字典类型列表
     *
     * @param sysDictType
     * @return AjaxResult
     */
    AjaxResult addDictType(SysDictType sysDictType);

    /**
     * 更新字典类型列表
     *
     * @param sysDictType
     * @return AjaxResult
     */
    AjaxResult editDictType(SysDictType sysDictType);

    /**
     * 清除缓存
     *
     * @param
     * @return AjaxResult
     */
    AjaxResult clearCache();


}