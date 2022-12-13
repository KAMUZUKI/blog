package com.mu.dao;

import redis.clients.jedis.Jedis;

/**
 * @author : MUZUKI
 * @date : 2022-10-22 14:59
 **/

public class RedisHelper {
    public static Jedis getReadisInstance(){
        Jedis jedis=new Jedis(DbProperties.getInstance().getProperty("redis.host"),
                Integer.parseInt(DbProperties.getInstance().getProperty("redis.port")));
        return jedis;
    }
}
