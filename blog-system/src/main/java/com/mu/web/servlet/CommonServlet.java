package com.mu.web.servlet;

import com.google.gson.GsonBuilder;
import com.mu.web.model.JsonModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 将一个Request转为 T 对象
 * CommonServlet是一个模板类，一些通用功能都定义在这
 * @author MUZUKI
 */

public abstract class CommonServlet extends HttpServlet {

    protected void writeJson(JsonModel jm, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        //加了serializeNulls,会将空值也序列化
        GsonBuilder gson=new GsonBuilder().serializeNulls();
        //Gson gson = new Gson();
        //利用Gson框架将json对象转为json格式的字符串
        String jsonString = gson.create().toJson(jm);
        out.println(jsonString);
        out.flush();
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        //解决request中参数编码的问题
        //request.setCharacterEncoding("utf-8");
        //TODO: 网站后台管理的功能：
        //1.统计客户端系统类型 客户端类型
        //2.取客户端的ip， 求出客户端的地址
        //3.防止盗链
        //4.识别用户是否重复请求
        super.service(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * 导入包：import javax.servlet.http.HttpServletResponse;
         * 接口参数中定义：HttpServletResponse response
         */

        // 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");

        // 允许前端带认证cookie：启用此项后，上面的域名不能为'*'，必须指定具体的域名，否则浏览器会提示
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // 提示OPTIONS预检时，后端需要设置的两个常用自定义头
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        System.out.println("doPost Enter");
        //取出op的值
        String op = request.getParameter("op");
        //获取当前servlet类中的方法
        //Method[] mt = this.getClass().getDeclaredMethods();
        try{
            //在判断op的值与那个方法名对应       getDeclaredMethod 取出方法 包括非public的方法
            Method m = this.getClass().getDeclaredMethod(op, HttpServletRequest.class, HttpServletResponse.class);
            if (m== null){
                //404
                out404("资源地址："+op, response);
            } else {
                //再invoke这个方法  method.invoke( this, req, resp)
                m.invoke(this, request, response);//相当于this.login(req, resp)
            }
        }catch (NoSuchMethodException ex){
            ex.printStackTrace();
            out404(ex.getMessage(), response);
        } catch (Exception e){
            e.printStackTrace();
            //将错误提示输出到客户端 给客户看
            out500(e.getMessage(), response);
        }
    }

    protected void out500(String message, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("服务器内部错误 500:<b>");
        out.println(message);
        out.println("</b>");
        out.flush();
    }

    protected void out404(String message, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("查无此资源 404:<b>");
        out.println(message);
        out.println("</b>");
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected <T> T parseRequestToT(HttpServletRequest request, Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //1.通过class字节码创建一个对象
        T obj = cls.newInstance();
        //2.从request中取出所有的属性，存到这个obj中
        Map<String,String[]>map=request.getParameterMap();
        //3.取出cls中所有的属性，看有几个和map中的键相同，相同则调用cls中的set方法，注入值
        Method[] ms = cls.getMethods();
        for(Method m:ms){
            if(m.getName().startsWith("set")){
                //这个m是一个setXxx（），取出Xxx是什么，根据Xxx，在 map.get（Xxx）取出这个值
                String fieldName=getFieldName(m);
                String[] values=map.get(fieldName);
                String v=null;
                if(values!=null){
                    if(values.length > 1){
                        StringBuffer sb = new StringBuffer();
                        for(String s : values){
                            sb.append(s + ",");
                        }
                        v = sb.toString();
                    } else {
                        v = values[0];
                    }
                }
                if( v==null ){
                    continue;
                }
                //判断这个m方法的参数类型，然后将v进行类型转换
                String methodParameterTypeName=m.getParameterTypes()[0].getTypeName();
                if("java.lang.Integer".equals(methodParameterTypeName) || "int".equals(methodParameterTypeName)){
                    Integer va = Integer.parseInt(v);
                    //然后在调用setXxx（），将值注入
                    m.invoke(obj,va);
                }else if("java.lang.Double".equals(methodParameterTypeName) || "double".equals(methodParameterTypeName)){
                    Double va = Double.parseDouble(v);
                    m.invoke(obj,va);
                }else if("java.lang.Float".equals(methodParameterTypeName) || "float".equals(methodParameterTypeName)){
                    Float va = Float.parseFloat(v);
                    m.invoke(obj,va);
                }else if("java.lang.Long".equals(methodParameterTypeName) || "long".equals(methodParameterTypeName)){
                    Long va = Long.parseLong(v);
                    m.invoke(obj,va);
                }else {
                    m.invoke(obj,v);
                }
            }
        }
        return obj;
    }
    private String getFieldName(Method setMethod){
        String fieldName=setMethod.getName().substring("set".length());
        //将fieldName的首字母改小写
        fieldName=fieldName.substring(0,1).toLowerCase()+ fieldName.substring(1);
        return fieldName;
    }
}
