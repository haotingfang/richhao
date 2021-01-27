package com.example.dao.service.impl;

import com.example.common.core.cache.RedisCache;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.SysDictData;
import com.example.common.core.domain.entity.SysDictType;
import com.example.common.utils.BeanUtils;
import com.example.common.utils.DictUtils;
import com.example.common.utils.SecurityUtils;
import com.example.common.utils.TableDataUtils;
import com.example.dao.mapper.SysDictDataMapper;
import com.example.dao.service.SysDictDataService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典数据表(SysDictData)表服务实现类
 *
 * @author makejava
 * @since 2021-01-21 14:40:58
 */
@Service("sysDictDataService")
public class SysDictDataServiceImpl implements SysDictDataService {
    @Resource
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 通过ID查询单条数据
     *
     * @param dictCode 主键
     * @return 实例对象
     */
    @Override
    public AjaxResult queryById(Long dictCode) {
        SysDictData sysDictData = sysDictDataMapper.selectDictDataById(dictCode);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("data", sysDictData);
        return ajaxResult;
    }

    @Override
    public TableDataInfo selectDictDataList(SysDictData sysDictData, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysDictData> list = sysDictDataMapper.selectDictDataList(sysDictData);
        TableDataInfo tableDataInfo = TableDataUtils.buildTableDataInfo(list);
        return tableDataInfo;
    }


    @Override
    @Transactional
    public int delete(Long[] sysDictDataIds) {
        int row = sysDictDataMapper.deleteDictDataByIds(sysDictDataIds);
        //更新缓存
        if(row>0){
            String dictTypeText = sysDictDataMapper.getDictTypeTextByIds(sysDictDataIds[0]);
            List<SysDictData> list = sysDictDataMapper.selectDictDataByType(dictTypeText);
            redisCache.setCacheObject(DictUtils.getCacheKey(dictTypeText), list);
        }
        return row;
    }

    @Override
    @Transactional
    public AjaxResult addDictData(SysDictData sysDictData) {
        BeanUtils.addBuildBean(sysDictData);
        int row = sysDictDataMapper.insertDictData(sysDictData);
        //更新缓存
        if(row>0){
            List<SysDictData> list = sysDictDataMapper.selectDictDataByType(sysDictData.getDictType());
            redisCache.setCacheObject(DictUtils.getCacheKey(sysDictData.getDictType()), list);
        }
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult editDictData(SysDictData sysDictData) {
        BeanUtils.editBuildBean(sysDictData);
        int row = sysDictDataMapper.updateDictData(sysDictData);
        //更新新的缓存
        if(row>0){
            List<SysDictData> list = sysDictDataMapper.selectDictDataByType(sysDictData.getDictType());
            redisCache.setCacheObject(DictUtils.getCacheKey(sysDictData.getDictType()), list);
        }
        return AjaxResult.success();
    }
}