package com.mu.bean;

import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2022-11-06 19:28
 * @description : 友链实体类
 **/

@Data
public class Flink {
    /**
     * 友链id
     */
    private Integer id;
    /**
     * 友链名称
     */
    private String name;
    /**
     * 友链地址
     */
    private String url;
    /**
     * 友链图标
     */
    private String img;
    /**
     * 友链描述
     */
    private String description;
    /**
     * 友链状态
     */
    private String status;
    /**
     * 友链排序
     */
    private Integer sort;
}
