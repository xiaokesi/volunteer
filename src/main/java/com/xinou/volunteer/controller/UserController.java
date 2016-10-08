package com.xinou.volunteer.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.render.CaptchaRender;
import com.jfinal.upload.UploadFile;
import com.xinou.volunteer.dao.User;
import com.xinou.volunteer.dao.UserActivities;
import com.xinou.volunteer.utils.IdUtil;
import com.xinou.volunteer.utils.MD5Util;
import com.xinou.volunteer.validator.RegisterValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制
 * Created by shizhida on 16/1/19.
 */
public class UserController extends Controller {

    @Before(GET.class)
    public void loginPage(){
        render("/resources/user/login.html");
    }
    @Before(GET.class)
    public void registerPage(){
        render("/resources/user/register.html");
    }

    public void captcha (){
        render(new CaptchaRender());
    }


    @Before(POST.class)
    public void login(){
        String uname = getPara("username");
        String pwd = MD5Util.MD5(getPara("password"));

        List<User> users = User.dao.find("select * from user where loginname=? and password=?",uname,pwd);

        if(users.size()==0){
            keepPara();
            setAttr("error","用户名或密码错误");
            render("/resources/user/login.html");
        }
        else {
            User user = users.get(0);
            setSessionAttr("user",user);
            redirect("/user/userInfo");
//            renderText("登录成功");
        }
    }


    public void uploadicon(){

        UploadFile file = getFile("head", "./update/heads/");

        String name = file.getFileName();

        Map<String,Object> obj = new HashMap<>();

        obj.put("name",name);

        setSessionAttr("lasticon",name);

        renderJson(obj);

    }

    @Before({POST.class,RegisterValidator.class})
    public void register(){

        System.out.println(1);
        String loginname = getPara("loginname");
        List<User> users = User.dao.find("select * from user where loginname=?",loginname);
        if(users.size()>0)
        {
            setAttr("error","用户已经被注册");
            render("/resource/user/register.html");
            return;
        }
        /**
         * 字段：
         * id
         * loginname 登录名
         * password  密码
         * realname  真实姓名
         * email     邮箱
         * idcard    身份证号
         *
         */
        User user = new User();

        user.put("id", IdUtil.getTimeMillisSequence());
        user.put("loginname",getPara("loginname"));

        user.put("password", MD5Util.MD5(getPara("password")));
        user.put("realname",getPara("realname"));
        user.put("email",getPara("email"));
        user.put("idcard",getPara("idcard"));

        user.put("area",getPara("area"));
        user.put("mobile", getPara("mobile"));

        user.put("zybj_id",getPara("zybj"));

        user.put("head",getSessionAttr("lasticon"));

        user.save();

        setSessionAttr("user",user);

//        renderText("注册成功");
        redirect("/user/userInfo");

    }

    public void userDetail(){
        String uid = getPara("uid");

        if("".equals(uid)||uid==null){
            uid = ((User)getSessionAttr("user")).getStr("id");
        }

        User u = User.dao.findById(uid);

        String[] names = u._getAttrNames();
        for (String name : names) {
            setAttr(name,u.get(name));
        }
        String idcard = u.getStr("idcard");
        setAttr("birthday",idcard.substring(6,14));

        render("/resources/user/userdetail.html");



    }

    public void userInfo(){

        String uid = getPara("uid");

        if("".equals(uid)||uid==null){
            uid = ((User)getSessionAttr("user")).getStr("id");
        }

        User u = User.dao.findById(uid);

        System.out.println(u);

        List<UserActivities> ua = UserActivities.dao.find("select * from user_activities where uid = ?", uid);

//        List<String> aids = new ArrayList<>();
//        for (UserActivities userActivities : ua) {
//            aids.add(userActivities.getStr("aid"));
//        }

//        List<Activities> acts = Activities.dao.find("select * from activities where aid in ?",aids);

        setAttr("icon", u.getStr("head"));
        setAttr("uname", u.getStr("realname"));
        setAttr("uid", u.getStr("id"));
        setAttr("acts", ua.size());

        String zybj = u.getStr("zybj_id");
        if("".equals(zybj)||zybj==null)
            setAttr("zybj","未绑定");
        else setAttr("zybj","已绑定");

        render("/resources/user/userinfo.html");

    }

}
