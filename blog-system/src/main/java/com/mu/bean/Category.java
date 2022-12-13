package com.mu.bean;

import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2022-11-06 19:23
 * @description : 栏目实体类
 **/
@Data
public class Category {
    /**
     * 栏目id
     */
    private Integer id;
    /**
     * 栏目名称
     */
    private String name;
    /**
     * 栏目排序
     */
    private Integer sort;
    /**
     * 栏目说明
     */
    private String description;
}
