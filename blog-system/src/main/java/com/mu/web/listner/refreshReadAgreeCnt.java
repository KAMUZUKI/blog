package com.mu.web.listner;

import com.mu.bean.Article;
import com.mu.dao.DbHelper;
import com.mu.dao.RedisHelper;
import com.mu.utils.Constants;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class refreshReadAgreeCnt {
    public static void main(String[] args) {
        refreshReadAgreeCnt rrc = new refreshReadAgreeCnt();
        rrc.refreshTimer();
    }

    public void refreshTimer() {
        refreshReadAgreeCnt rrc=new refreshReadAgreeCnt();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                rrc.refresh();
                System.out.println("-------refresh--------");
            }
        }, 1000, 2000);
    }

    /**
     * 更新数据库里面点赞数和文章阅读量
     */
    public void refresh() {
        DbHelper db = new DbHelper();
        Jedis jedis = RedisHelper.getReadisInstance();
        String sql = "select id from article";
        List<Article> list = null;
        try {
            //先取出所有文章的id
            list = db.select(sql, Article.class);
            if (list != null && list.size() > 0) {
                //如果不为空,就循环这些文章
                for (Article a : list) {
                    //如果redis里面有这篇文章的浏览数
                    if (jedis.exists(a.getId() + Constants.REDIS_ARTICLE_READCNT)) {
                        //将浏览数取出
                        String readeCnt = jedis.get(a.getId() + Constants.REDIS_ARTICLE_READCNT);
                        //更新数据库
                        sql = "update article set readCnt=? where id=?";
                        db.doUpdata(sql, readeCnt, a.getId());
                    }
                    if (jedis.exists(a.getId() + Constants.REDIS_ARTICLE_PRAISE)) {
                        Long agreeCnt = jedis.scard(a.getId() + Constants.REDIS_ARTICLE_PRAISE);
                        sql = "update article set agreeCnt=? where id=?";
                        db.doUpdata(sql, agreeCnt, a.getId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
