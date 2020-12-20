package com.example.dao.service.impl;

import com.example.common.constant.Constants;
import com.example.common.core.cache.RedisCache;
import com.example.common.core.text.Convert;
import com.example.common.utils.StringUtils;
import com.example.dao.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
        if (StringUtils.isNotEmpty(configValue))
        {
            return configValue;
        }
//        SysConfig config = new SysConfig();
//        config.setConfigKey(configKey);
//        SysConfig retConfig = configMapper.selectConfig(config);
//        if (StringUtils.isNotNull(retConfig))
//        {
//            redisCache.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
//            return retConfig.getConfigValue();
//        }
        return StringUtils.EMPTY;
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
