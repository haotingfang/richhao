package com.example.dao.service.impl;

import com.example.common.core.domain.AjaxResult;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Card;
import com.example.common.core.domain.entity.UserInfo;
import com.example.common.enums.DelFlag;
import com.example.common.enums.Status;
import com.example.common.utils.BeanUtils;
import com.example.common.utils.TableDataUtils;
import com.example.dao.mapper.CardMapper;
import com.example.dao.service.CardService;
import com.github.pagehelper.PageHelper;
import jdk.net.SocketFlow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Card)表服务实现类
 *
 * @author makejava
 * @since 2021-02-05 15:09:17
 */
@Service("cardService")
public class CardServiceImpl implements CardService {
    @Resource
    private CardMapper cardMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Card queryById(Long id) {
        return this.cardMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param pageSize 一页数量
     * @param pageNum  当前页
     * @return 对象列表
     */
    @Override
    public TableDataInfo queryAllByLimit(Card card, Integer pageSize , Integer pageNum){
        PageHelper.startPage(pageNum, pageSize);
        List<Card> list = cardMapper.queryAllByLimit(card);
        TableDataInfo tableDataInfo = TableDataUtils.buildTableDataInfo(list);
        return tableDataInfo;
    }

    /**
     * 新增数据
     *
     * @param card 实例对象
     * @return 实例对象
     */
    @Override
    public AjaxResult insert(Card card) {
        card.setDelFlag(DelFlag.EXIST.getCode());
        card.setStatus(Status.EXIST.getCode());
        BeanUtils.addBuildBean(card);
        this.cardMapper.insert(card);
        return AjaxResult.success();
    }

    /**
     * 修改数据
     *
     * @param card 实例对象
     * @return 实例对象
     */
    @Override
    public AjaxResult update(Card card) {
        this.cardMapper.update(card);
        return AjaxResult.success();
    }

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public int deleteByIds(Long[] ids) {
        return this.cardMapper.deleteByIds(ids) ;
    }
}