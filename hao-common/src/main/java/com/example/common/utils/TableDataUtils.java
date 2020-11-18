package com.example.common.utils;

import com.example.common.constant.HttpStatus;
import com.example.common.core.domain.TableDataInfo;
import com.example.common.core.domain.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class TableDataUtils {

    public static TableDataInfo buildTableDataInfo(List<?> list){
        PageInfo<?> pageInfo=new PageInfo<>(list);
        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setCode(HttpStatus.SUCCESS);
        tableDataInfo.setMsg("操作成功");
        tableDataInfo.setData(list);
        tableDataInfo.setCurrIndex(pageInfo.getPageNum());
        tableDataInfo.setNextIndex(pageInfo.getNextPage());
        tableDataInfo.setPreIndex(pageInfo.getPrePage());
        tableDataInfo.setPageSize(pageInfo.getPageSize());
        tableDataInfo.setTotalNum(pageInfo.getTotal());
        tableDataInfo.setTotalPage(pageInfo.getPages());
        return tableDataInfo;
    }
}
