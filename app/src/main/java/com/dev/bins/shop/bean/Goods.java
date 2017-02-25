package com.dev.bins.shop.bean;

import java.util.List;

/**
 * Created by bin on 25/02/2017.
 */

public class Goods {

    private  int currentPage;
    private  int pageSize;
    private  int totalPage;
    private  int totalCount;

    private List<GoodsItem> list;

    public List<GoodsItem> getList() {
        return list;
    }

    public void setList(List<GoodsItem> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
