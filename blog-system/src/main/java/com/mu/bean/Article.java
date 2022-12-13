package com.mu.bean;


import lombok.Data;

import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-11-06 19:16
 * @description : 文章实体类
 **/

@Data
public class Article {
    /**
     * 文章id
     */
    private Integer id;
    /**
     * 作者
     */
    private String author;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 关键字 用于词云快速浏览
     */
    private String keyWords;
    /**
     * 描述
     */
    private String description;
    /**
     * 栏目
     */
    private Integer categoryId;
    /**
     * 标签，用逗号分隔
     */
    private String label;
    /**
     * 标题图片
     */
    private String titleImgs;
    /**
     * 状态 0：草稿 1：发布
     */
    private String status;
    /**
     * 发布时间
     */
    private String createTime;
    /**
     * 阅读量
     */
    private Integer readCnt;
    /**
     * 点赞数
     */
    private Integer agreeCnt;

    /**
     * 评论数
     */
    private Integer commentCnt;

    /**
     * 用户点过赞的文章
     */
    private Integer[] likeData;
}
