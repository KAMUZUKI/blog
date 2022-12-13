package com.mu.web.listner;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author : MUZUKI
 * @date : 2022-10-16 14:50
 **/

@WebListener
public class ResSessionChangeListner implements HttpSessionAttributeListener {
    public ResSessionChangeListner(){
        System.out.println("ResSessionChangeListner的构造方法执行");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("添加属性:");
        String key = event.getName();
        Object value = event.getValue();
        System.out.println(key + " : " + value);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("删除属性:");
        String key = event.getName();
        Object value = event.getValue();
        System.out.println(key + " : " + value);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("替换属性:");
        String key = event.getName();
        Object value = event.getValue();
        System.out.println(key + " : " + value);
    }
}
