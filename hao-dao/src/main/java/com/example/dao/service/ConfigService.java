package com.example.dao.service;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.SysConfig;

public interface ConfigService {

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    public String selectConfigByKey(String configKey);

    /**
     * 根据id查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    public SysConfig selectConfigById(Long configId);

    /**
     * 根据键名查询参数配置信息
     *
     * @param sysConfig 请求参数
     * @param pageSize 当前页
     * @param pageNum 一页数量
     * @return 参数键值
     */
    public TableDataInfo selectConfigList(SysConfig sysConfig, Integer pageSize , Integer pageNum);

    /**
     * 根据ID删除参数设置
     *
     * @param configIds 参数设置ID集合
     * @return 影响条数
     */
    public int delete(Long[] configIds);

    public AjaxResult addConfig(SysConfig sysConfig);

    public AjaxResult editConfig(SysConfig sysConfig);

    public AjaxResult clearCache();

}
