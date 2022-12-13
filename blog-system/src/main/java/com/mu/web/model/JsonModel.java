package com.mu.web.model;


import com.mu.bean.Article;

/**
 * @author MUZUKI
 */


public class JsonModel {
    /**
     * 0失败  1成功
     */
    private Integer code;
    /**
     * 当code=0时的错误信息
     */
    private String msg;
    /**
     * 当code=1时，操作的结果数据
     */
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData(Article article) {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
