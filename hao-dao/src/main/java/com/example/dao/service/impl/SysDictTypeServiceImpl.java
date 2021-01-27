package com.example.dao.service.impl;

import com.example.common.constant.Constants;
import com.example.common.core.cache.RedisCache;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.SysConfig;
import com.example.common.core.domain.entity.SysDictData;
import com.example.common.core.domain.entity.SysDictType;
import com.example.common.enums.DelFlag;
import com.example.common.utils.*;
import com.example.dao.mapper.SysDictDataMapper;
import com.example.dao.mapper.SysDictTypeMapper;
import com.example.dao.service.SysDictTypeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 字典类型表(SysDictType)表服务实现类
 *
 * @author makejava
 * @since 2021-01-21 15:41:04
 */
@Service("sysDictTypeService")
public class SysDictTypeServiceImpl implements SysDictTypeService {
    @Resource
    private SysDictTypeMapper sysDictTypeMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        List<SysDictData> dictDatas = DictUtils.getDictCache(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            return dictDatas;
        }
        dictDatas = sysDictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }


    /**
     * 通过ID查询单条数据
     *
     * @param dictId 主键
     * @return 实例对象
     */
    @Override
    public AjaxResult queryById(Long dictId) {
        SysDictType sysDictType = sysDictTypeMapper.queryById(dictId);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("data" , sysDictType);
        return ajaxResult;
    }

    @Override
    public TableDataInfo selectDictTypeList(SysDictType sysDictType, Integer pageSize, Integer pageNum) {
        if (StringUtils.isNotNull(pageSize) && StringUtils.isNotNull(pageNum)) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<SysDictType> list = sysDictTypeMapper.selectDictTypeList(sysDictType);
        TableDataInfo tableDataInfo = TableDataUtils.buildTableDataInfo(list);
        return tableDataInfo;
    }

    @Override
    public int delete(Long[] sysDictTypeIds) {
        String userName = SecurityUtils.getUserName();
        int row = sysDictTypeMapper.deleteByIds(sysDictTypeIds, userName);
        return row;
    }

    @Override
    @Transactional
    public AjaxResult addDictType(SysDictType sysDictType) {
        BeanUtils.addBuildBean(sysDictType);
        sysDictType.setDelFlag(DelFlag.EXIST.getCode());
        sysDictTypeMapper.insert(sysDictType);
        return AjaxResult.success();
    }

    @Override
    @Transactional
    public AjaxResult editDictType(SysDictType sysDictType) {
        BeanUtils.editBuildBean(sysDictType);
        SysDictType oldSysDictType = sysDictTypeMapper.queryById(sysDictType.getDictId());
        sysDictTypeMapper.update(sysDictType);
        String oldSysDictTypeText = oldSysDictType.getDictType();
        String newSysDictTypeText = sysDictType.getDictType();
        //更新sys_dict_datas数据
        if(oldSysDictTypeText.equals(newSysDictTypeText)){
            sysDictDataMapper.updateDictDataType(oldSysDictTypeText,newSysDictTypeText);
            //删除老的缓存
            redisCache.deleteObject(DictUtils.getCacheKey(oldSysDictTypeText));
            //更新新的缓存
            List<SysDictData> list = sysDictDataMapper.selectDictDataByType(newSysDictTypeText);
            redisCache.setCacheObject(DictUtils.getCacheKey(newSysDictTypeText),list);
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult clearCache() {
        Collection<String> keys = redisCache.keys(Constants.SYS_DICT_KEY + "*");
        redisCache.deleteObject(keys);
        return AjaxResult.success();
    }


}