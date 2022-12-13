package com.mu.web.servlet;

import com.mu.bean.Article;
import com.mu.bean.Flink;
import com.mu.bean.User;
import com.mu.dao.DbHelper;
import com.mu.dao.RedisHelper;
import com.mu.utils.Constants;
import com.mu.utils.Md5;
import com.mu.utils.SendMail;
import com.mu.web.model.JsonModel;
import lombok.val;
import redis.clients.jedis.Jedis;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @date : 2022-11-06 19:54
 * @description : 用户服务
 **/
@WebServlet(name = "UserServlet", value = "/user.action")
public class UserServlet extends CommonServlet {
    /**
     * user.action?op=addFlink
     * 添加友链
     */
    protected void addFlink(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        Flink flink=new Flink();
        int i=0;
        String sql="insert into flink(name,url,img,description,status,sort) values(?,?,?,?,'1',?)";
        try{
            flink=super.parseRequestToT(request,Flink.class);
            i=db.doUpdata(sql,flink.getName(),flink.getUrl(),flink.getImg(),flink.getDescription(),flink.getSort());
        }catch ( Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm,response);
            return;
        }
        if(i!=1){
            jm.setCode(0);
            jm.setMsg("添加失败！");
            super.writeJson(jm,response);
            return;
        }
        jm.setCode(1);
        jm.setData(flink);
        super.writeJson(jm,response);
    }

    /**
     * user.action?op=alterPassword
     * 修改密码
     */
    protected void alterPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        DbHelper db=new DbHelper();
        User user=new User();
        String emailCode = "";
        String email = "";
        int i=0;
        String sql="update user set name=?,pwd=?,email=? where id=?";
        try {
            HttpSession session=request.getSession();
            emailCode=session.getAttribute("emailCode").toString();
            System.out.println(emailCode);
            user=super.parseRequestToT(request,User.class);
            if(user.getEmail()!=null&&emailCode.equals(user.getEmail())){
                i=db.doUpdata(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getId());
            }else {
                jm.setCode(0);
                jm.setMsg("验证码错误！");
                super.writeJson(jm, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        if(i==0){
            jm.setCode(0);
            jm.setMsg("更改失败！");
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(user);
        super.writeJson(jm, response);
    }

    /**
     * user.action?op=sendEmail
     * 发送邮箱验证码
     */
    protected void sendEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        String emailCode = "";
        String email = "";
        try {
            //生成一个六位数的随机数验证码
            emailCode = (Math.random() + "").substring(2, 8);
            //将这个验证码存到session里面
            HttpSession session = request.getSession();
            session.setAttribute("emailCode", emailCode);
            //取出用户的email参数
            email = request.getParameter("email");
            SendMail sendMail = new SendMail();
            sendMail.sendMail(email, emailCode);
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        jm.setCode(1);
        jm.setData(emailCode);
        super.writeJson(jm, response);
    }

    /**
     * user.action?op=register
     * 注册
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        User user = new User();
        String account;
        String sql = "insert into user(username, account, pwd, phone, email, head,createTime,status,type) values(?, ?, ?, ?, ?, ?, now(), 1, 2)";
        int result = 0;
        try {
            user = super.parseRequestToT(request, User.class);
            user.setPassword(Md5.getInstance().getMD5(user.getPassword()));
            List<Map<String, Object>> list = db.select("select max(account)id from user");
            int id = Integer.parseInt(list.get(0).get("id").toString()) + 1;
            account = String.valueOf(id);
            result = db.doUpdata(sql, user.getUsername(),account, user.getPassword(), user.getPhone(), user.getEmail(), user.getHead());
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
        if (result == 0) {
            jm.setCode(0);
            jm.setMsg("注册失败...");
        } else {
            jm.setCode(1);
            jm.setMsg("注册成功,id为"+ account+"请牢记！");
            jm.setData(user);
        }
        super.writeJson(jm, response);

    }

    /**
     * user.action?op=login
     * 登录
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        Jedis jedis= RedisHelper.getReadisInstance();
        User user = new User();
        try {
            user = super.parseRequestToT(request, User.class);
            String valcode = request.getParameter("valcode");
            HttpSession session = request.getSession();
            boolean validate = false;
            if (validate) {
                String code = session.getAttribute("code").toString();
                if (!code.equals(valcode)) {
                    jm.setCode(0);
                    jm.setMsg("验证码错误");
                    super.writeJson(jm, response);
                    return;
                }
            }
            user.setPassword(Md5.getInstance().getMD5(user.getPassword()));
            String sql = "select * from user where account=? and pwd=?";
            List<User> list = db.select(sql, User.class, user.getUsername(), user.getPassword());
            if (list != null && list.size() > 0) {
                User rs = list.get(0);
                //从redis中取出这个用户点过赞的所有文章id
                //1.先判断这个用户是否点过赞,没有的话直接设置user里面的likeList为空
                if(!jedis.exists(rs.getId()+ Constants.REDIS_USER_PRAISE)){
                    rs.setLikeList(null);
                }else{
                    Set<String> set =jedis.smembers(rs.getId()+ Constants.REDIS_USER_PRAISE);
                    List<Integer> likeList=new ArrayList<Integer>();
                    if(set.size()>0){
                        for (String str:set){
                            likeList.add(Integer.parseInt(str));
                        }
                    }
                    rs.setLikeList(likeList);
                }
                rs.setPassword("不要偷看");
                jm.setCode(1);
                jm.setData(rs);
                session.setAttribute("user", rs);
                super.writeJson(jm, response);
                return;
            } else {
                jm.setCode(0);
                jm.setMsg("用户名或密码错误");
                super.writeJson(jm, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm, response);
            return;
        }
    }

    /**
     * user.action?op=deleteUser
     * 登录
     */
    protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        int userId = Integer.parseInt(request.getParameter("userId"));
        String sql = "delete from user where id = ?";
        int result = 0;
        try{
            result = db.doUpdata(sql,userId);
        }catch (Exception e){
            e.printStackTrace();
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            super.writeJson(jm,response);
            return;
        }
        if(result == 0){
            jm.setCode(0);
        }else if(result == 1){
            jm.setCode(1);
        }
        super.writeJson(jm,response);
    }

    protected void alterUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonModel jm = new JsonModel();
        DbHelper db = new DbHelper();
        User user = new User();
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        String sql = "update user set status=?,type=? where id = ?";
        int result = 0;
        try{
            result = db.doUpdata(sql,status,type,id);
        }catch(Exception e){
            jm.setCode(0);
            jm.setMsg(e.getMessage());
            e.printStackTrace();
            super.writeJson(jm,response);
            return;
        }
        if(result==0){
            jm.setCode(0);
            jm.setMsg("更新失败");
        }else if(result == 1){
            jm.setCode(1);
            jm.setData("更新成功");
        }
        super.writeJson(jm,response);
    }
}
