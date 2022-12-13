package com.mu.bean;

import lombok.Data;

import java.util.List;

/**
 * @author : MUZUKI
 * @date : 2022-11-06 19:31
 * @description : 用户实体类
 **/

@Data
public class User {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String head;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 状态		0 停用	1启用
     */
    private String status;
    /**
     * 用户类型 1系统管理员		2普通管理员3一般用户
     */
    private String type;

    /**
     * 用户点过赞的文章
     */
    private List<Integer> likeList;
}
