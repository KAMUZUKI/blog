package com.mu.web.filter;

import com.google.gson.Gson;
import com.mu.web.model.JsonModel;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


/**
 * @author : MUZUKI
 * @date : 2022-10-15 19:36
 * @description : 通用过滤器
 **/

public abstract class CommonFilter implements Filter {
    protected void writeJson(Map map, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        //将json对象转换为json字符串
        String jsonString = gson.toJson(map);
        out.println(jsonString);
        out.flush();
    }

    protected void writeJson(JsonModel jm, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        //把对象转成json字符串
        String jsonString = gson.toJson(jm);
        out.println(jsonString);
        out.flush();
    }
}
