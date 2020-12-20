package com.example.common.core.domain.entity;

import com.example.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 参数配置表(SysConfig)实体类
 *
 * @author hao
 * @since 2020-12-20 21:30:17
 */
@Data
public class SysConfig extends BaseEntity {
    private static final long serialVersionUID = 656456973076324589L;
    /**
     * 参数主键
     */
    private Integer configId;
    /**
     * 参数名称
     */
    private String configName;
    /**
     * 参数键名
     */
    private String configKey;
    /**
     * 参数键值
     */
    private String configValue;
    /**
     * 系统内置（Y是 N否）
     */
    private String configType;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;


}
