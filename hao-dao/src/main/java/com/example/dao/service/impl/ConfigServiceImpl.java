package com.example.dao.service.impl;

import com.example.common.constant.Constants;
import com.example.common.core.cache.RedisCache;
import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.SysConfig;
import com.example.common.core.domain.entity.UserInfo;
import com.example.common.core.text.Convert;
import com.example.common.enums.DelFlag;
import com.example.common.utils.BeanUtils;
import com.example.common.utils.SecurityUtils;
import com.example.common.utils.StringUtils;
import com.example.common.utils.TableDataUtils;
import com.example.dao.mapper.SysConfigMapper;
import com.example.dao.service.ConfigService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysConfigMapper configMapper;

    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
        if (StringUtils.isNotEmpty(configValue))
        {
            return configValue;
        }
        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig = configMapper.selectConfig(config);
        if (StringUtils.isNotNull(retConfig))
        {
            redisCache.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public SysConfig selectConfigById(Long configId) {
        SysConfig config = new SysConfig();
        config.setConfigId(configId);
        SysConfig sysConfig = configMapper.selectConfig(config);
        return sysConfig;
    }

    @Override
    public TableDataInfo selectConfigList(SysConfig sysConfig, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysConfig> list = configMapper.selectConfigList(sysConfig);
        TableDataInfo tableDataInfo = TableDataUtils.buildTableDataInfo(list);
        return tableDataInfo;
    }

    @Override
    @Transactional
    public int delete(Long[] configIds) {
        String userName = SecurityUtils.getUserName();
        //获取删除的所有keys
        List keys = configMapper.getDeleteKeys(configIds);
        int i= configMapper.deleteByIds(configIds,userName);
        if(i>0){
            redisCache.deleteObject(keys);
        }
        return i;
    }

    @Override
    @Transactional
    public AjaxResult addConfig(SysConfig sysConfig) {
        BeanUtils.addBuildBean(sysConfig);
        sysConfig.setDelFlag(DelFlag.EXIST.getCode());
        //插入角色数据
        int row = configMapper.insert(sysConfig);
        //更新缓存
        if (row > 0) {
            redisCache.setCacheObject(getCacheKey(sysConfig.getConfigKey()), sysConfig.getConfigValue());
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult editConfig(SysConfig sysConfig) {
        BeanUtils.editBuildBean(sysConfig);
        int row = configMapper.update(sysConfig);
        //更新缓存
        if (row > 0) {
            redisCache.setCacheObject(getCacheKey(sysConfig.getConfigKey()), sysConfig.getConfigValue());
        }
        return AjaxResult.success();
    }

    @Override
    public AjaxResult clearCache() {
        Collection<String> keys = redisCache.keys(Constants.SYS_CONFIG_KEY + "*");
        redisCache.deleteObject(keys);
        return AjaxResult.success();
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey)
    {
        return Constants.SYS_CONFIG_KEY + configKey;
    }
}
