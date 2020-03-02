package com.github.vanlla.core.util;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(
        description = "分页"
)
public class PageUtils<E> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            value = "总记录数",
            example = "50"
    )
    private long totalCount;
    @ApiModelProperty(
            value = "每页记录数",
            example = "10"
    )
    private long pageSize;
    @ApiModelProperty(
            value = "总页数",
            example = "5"
    )
    private long totalPage;
    @ApiModelProperty(
            value = "当前页数",
            example = "1"
    )
    private long currPage;
    private List<E> list;

    public PageUtils() {
    }

    public PageUtils(List<E> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = (long) totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (long) ((int) Math.ceil((double) totalCount / (double) pageSize));
    }

    public PageUtils(IPage<E> page) {
        this.list = page.getRecords();
        this.totalCount = (long) page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = (long) page.getPages();
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = (long) totalCount;
    }

    public long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = (long) totalPage;
    }

    public long getCurrPage() {
        return this.currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<E> getList() {
        return this.list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}