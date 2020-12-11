package com.example.common.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TableDataInfo implements Serializable {

    private long totalNum;

    private int currIndex;

    private int nextIndex;

    private int pageSize;

    private int preIndex;

    private int totalPage;

    private List<?> rows;

    private int code;

    private String msg;


}
