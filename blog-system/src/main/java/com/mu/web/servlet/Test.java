package com.mu.web.servlet;

import com.google.gson.GsonBuilder;
import com.mu.bean.Comment;
import com.mu.dao.DbHelper;
import com.mu.dao.RedisHelper;
import com.mu.utils.Constants;
import com.mu.web.model.JsonModel;
import redis.clients.jedis.Jedis;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class Test {


    public static void main(String[] args) {
        Test test = new Test();
//        test.getComments("1");
        test.testReids();

    }


    protected void getComments(String articleId) {
        DbHelper db = new DbHelper();
        Comment comment = new Comment();
        List<Comment> list = null;
        String sql = "select comment.id,content,user.name createBy,head,comment.createTime" +
                " from comment,user " +
                "where comment.createBy=user.id and articleId=?";
        try {
            list = db.select(sql, Comment.class, articleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.size() > 0) {
            for (Comment c : list) {
                //Gson gson=new Gson();
                GsonBuilder gson = new GsonBuilder().serializeNulls();
                String jsonString = gson.create().toJson(c);
//                System.out.println(gson.serializeNulls());
//                String jsonString = gson.toString();
                System.out.println(jsonString);
            }
        }
    }

    public void testReids() {
        Jedis jedis = RedisHelper.getReadisInstance();
//        jedis.set("测试2", "10");
        jedis.incr("测试2");
    }
}
