package com.example.dao.service.impl;

import com.example.common.core.domain.entity.SysDictData;
import com.example.common.utils.DictUtils;
import com.example.common.utils.StringUtils;
import com.example.dao.mapper.SysDictDataMapper;
import com.example.dao.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictTypeServiceImpl implements DictTypeService {

    @Autowired
    private SysDictDataMapper dictDataMapper;


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
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas))
        {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }
}
