package com.mu.utils;

/**
 * @author : MUZUKI
 * @date : 2022-10-21 20:37
 * @description : 用于保存常量信息
 **/

public class MuConstants {
    /**
     * redis中保存的用户历史访问记录
     */
    public static final String REDIS_VISITED="_visited";

    /**
     * redis中保存的用户点赞记录
     */
    public static final String REDIS_PRAISE="_PRAISE";

    /**
     * redis中保存的菜品点赞数
     */
    public final static String REDIS_FOOD_PRAISE="_food_praise";

    /**
     * redis中保存 使用某种浏览器登录网站 的 用户列表
     */
    public static final String REDIS_DEVICE_USERS="_device_users";

    /**
     * redis中保存 使用某种浏览器登录网站 的 设备列表
     */
    public static final String REDIS_USER_DEVICE="_user_device";
    /**
     * 系统中保存的登录用户 键名
     */
    public static final String RESUSER="user";

    /**
     * 系统中购物车的键名
     */
    public static final String CART="cart";
}
