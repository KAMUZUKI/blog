package com.mu.web.model;



import java.util.List;


public class PageBean<T> {
    /**
     * 界面给的已知参数
     * 当前第几页
     */
    private int pageno=1;
    /**
     * 每页多少条
     */
    private int pagesize=5;
    /**
     * 排序列名
     */
    private String sortby;
    /**
     * 排序方式 asc/desc
     */
    private String sort;
    /**
     * 数据库查询
     * 总记录数
     */
    private long total;
    /**
     * 需要在业务层计算
     */
    private List<T> dataset;
    /**
     * 上一页的页数
     */
    private int pre;
    /**
     * 下一页的页数
     */
    private int next;
    /**
     * 总共多少页
     */
    private int totalpages;

    public List<T> getDataset() {
        return dataset;
    }

    public int getPageno() {
        return pageno;
    }

    public void setPageno(int pageno) {
        this.pageno = pageno;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getSortby() {
        return sortby;
    }

    public void setSortby(String sortby) {
        this.sortby = sortby;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setDataset(List<T> dataset) {
        this.dataset = dataset;
    }

    public int getPre() {
        return pre;
    }

    public void setPre(int pre) {
        this.pre = pre;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }
}
