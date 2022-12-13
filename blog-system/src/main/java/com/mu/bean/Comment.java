package com.mu.bean;

import lombok.Data;

/**
 * @author : MUZUKI
 * @date : 2022-11-06 19:26
 * @description : 评论实体类
 **/

@Data
public class Comment {
    /**
     * 评论id
     */
    private Integer id;
    /**
     * 评论文章id
     */
    private Integer articleId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论人
     */
    private String createBy;
    /**
     * 评论时间
     */
    private String createTime;
    /**
     * 评论人的头像
     */
    private String head;
}
