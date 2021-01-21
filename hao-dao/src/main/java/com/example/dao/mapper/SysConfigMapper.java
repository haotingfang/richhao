package com.example.dao.mapper;

import com.example.common.core.domain.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 参数配置表(SysConfig)表数据库访问层
 *
 * @author hao
 * @since 2020-12-20 21:34:35
 */
@Mapper
public interface SysConfigMapper {

    /**
     * 查询参数配置信息
     *
     * @param config 参数配置信息
     * @return 参数配置信息
     */
    public SysConfig selectConfig(SysConfig config);

    /**
     * 通过ID查询单条数据
     *
     * @param configId 主键
     * @return 实例对象
     */
    SysConfig queryById(Long configId);

    List<String> getDeleteKeys(@Param("configIds") Long[] configIds);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysConfig> selectConfigList(SysConfig sysConfig);



    /**
     * 新增数据
     *
     * @param sysConfig 实例对象
     * @return 影响行数
     */
    int insert(SysConfig sysConfig);


    /**
     * 新增或按主键更新数据
     *
     * @param sysConfig  实例对象
     * @return 影响行数
     */
    int insertOrUpdate(SysConfig sysConfig);


    /**
     * 修改数据
     *
     * @param sysConfig 实例对象
     * @return 影响行数
     */
    int update(SysConfig sysConfig);

    /**
     * 通过主键删除数据
     *
     * @param configId 主键
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") Long[] ids, @Param("updateBy") String updateBy);

}
