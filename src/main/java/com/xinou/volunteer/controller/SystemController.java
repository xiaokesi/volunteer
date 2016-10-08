package com.xinou.volunteer.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.xinou.volunteer.dao.Activities;
import com.xinou.volunteer.dao.Organization;
import com.xinou.volunteer.dao.User;
import com.xinou.volunteer.utils.MD5Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbo on 16/6/29.
 * 后台管理系统
 */
public class SystemController extends Controller {

    @Before(GET.class)
    public void adminLogin() {
        render("/resources/system/login.html");
    }

    @Before(GET.class)
    public void adminUser() {
        List<User> userList = User.dao.find("select * from user");
        List<User> result = new ArrayList<>();
        //把admin去掉
        for (User info : userList) {
            if (!"1".equals(info.get("id"))) {
                result.add(info);
            }
        }
        setAttr("activitys", result);
        System.out.println("adminUser:" + User.dao.find("select * from user"));
        render("/resources/system/user.html");
    }


    @Before(GET.class)
    public void adminActivities() {
        List<Activities> activities = Activities.dao.find("select * from activities");
        setAttr("activitys", activities);
        System.out.println("adminActivities:" + Activities.dao.find("select * from activities"));
        render("/resources/system/activities.html");
    }

    @Before(GET.class)
    public void adminOrganization() {
        List<Organization> organizations = Organization.dao.find("select * from organization");
        setAttr("activitys", organizations);
        System.out.println("adminOrganization:" + Organization.dao.find("select * from organization"));
        render("/resources/system/organization.html");
    }

    @Before(GET.class)
    public void adminAddOrganization() {
        render("/resources/system/add_org.html");
    }

    @Before(POST.class)
    public void adminAddOrgAction() {
        Organization organization = new Organization();
        organization.set("org_name",getPara("org_name"));
        organization.set("leader",getPara("leader"));
        organization.set("contact",getPara("contact"));
        organization.set("org_desc",getPara("org_desc"));
        organization.save();
        redirect("/adminOrganization");
    }


    @Before(POST.class)
    public void adminActivitiesByType() {
        String activities_type = getPara("activities_type");
        if ("".equals(activities_type)||activities_type==null){
            redirect("/adminActivities");
            return;
        }
        List<Activities> activities = Activities.dao.find("select * from activities where type = ?",activities_type);
        setAttr("activitys", activities);
        render("/resources/system/activities.html");
    }


    @Before(POST.class)
    public void adminUserByName() {
        String loginname = getPara("loginname");
        if ("".equals(loginname)||loginname==null){
            redirect("/adminUser");
            return;
        }
        List<User> userList = User.dao.find("select * from user where loginname = ?",loginname);
        setAttr("activitys", userList);
        render("/resources/system/user.html");
    }

    @Before(POST.class)
    public void adminOrgByName() {
        String org_name = getPara("org_name");
        if ("".equals(org_name)||org_name==null){
            redirect("/adminOrganization");
            return;
        }
        List<Organization> organizations = Organization.dao.find("select * from organization where org_name = ?",org_name);
        setAttr("activitys", organizations);
        render("/resources/system/organization.html");
    }



    @Before(GET.class)
    public void adminIndex() {
        setAttr("user_count", User.dao.find("select count(*) as num from user").get(0).get("num"));
        render("/resources/system/index.html");
    }


    @Before(GET.class)
    public void adminUpdateActivities() {
        System.out.println("updateActivitiesId:" + getPara("activities_id"));
        List<Activities> activities = Activities.dao.find("select * from activities where id=?", getPara("activities_id"));
        if (activities != null && activities.size() != 0) {
            setAttr("update_activities", activities.get(0));
        }
        render("/resources/system/update_activities.html");
    }


    @Before(GET.class)
    public void adminUpdateUser() {
        System.out.println("updateUserId:" + getPara("user_id"));
        List<User> users = User.dao.find("select * from user where id=?", getPara("user_id"));
        if (users != null && users.size() != 0) {
            setAttr("update_user", users.get(0));
        }
        render("/resources/system/update_user.html");
    }

    @Before(GET.class)
    public void adminUpdateOrg() {
        System.out.println("updateOrgId:" + getPara("org_id"));
        List<Organization> organizations = Organization.dao.find("select * from organization where id=?", getPara("org_id"));
        if (organizations != null && organizations.size() != 0) {
            setAttr("update_org", organizations.get(0));
        }
        render("/resources/system/update_org.html");
    }


    @Before(GET.class)
    public void adminDeleteActivitiesAction() {
        Activities activities = new Activities();
        activities.set("id",getPara("activities_id"));
        activities.delete();
        redirect("/adminActivities");
    }


    @Before(GET.class)
    public void adminDeleteOrgAction() {
        Organization organization = new Organization();
        organization.set("id",getPara("org_id"));
        organization.delete();
        redirect("/adminOrganization");
    }

    @Before(GET.class)
    public void adminDeleteUserAction() {
        User user = new User();
        user.set("id",getPara("user_id"));
        user.delete();
        redirect("/adminUser");
    }


    @Before(POST.class)
    public void adminUpdateActivitiesAction(){
        Activities activities = new Activities();
        activities.set("id",getPara("id"));
        activities.set("title",getPara("title"));
        activities.set("time",getPara("time"));
        activities.set("size",getPara("size"));
        activities.set("type",getPara("type"));
        activities.set("context",getPara("context"));
        activities.update();
        redirect("/adminActivities");
    }

    @Before(POST.class)
    public void adminUpdateOrgAction(){
        Organization organization = new Organization();
        organization.set("id",getPara("id"));
        organization.set("org_name",getPara("org_name"));
        organization.set("leader",getPara("leader"));
        organization.set("contact",getPara("contact"));
        organization.set("org_desc",getPara("org_desc"));
        organization.update();
        redirect("/adminOrganization");
    }

    @Before(POST.class)
    public void adminUpdateUserAction() {
        String id = getPara("id");
        String loginname = getPara("loginname");
        String realname = getPara("realname");
        String email = getPara("email");
        String idcard = getPara("idcard");
        String area = getPara("area");
        String mobile = getPara("mobile");

        User user = new User();
        user.set("id", id);
        user.set("loginname", loginname);
        user.set("realname", realname);
        user.set("email", email);
        user.set("idcard", idcard);
        user.set("area", area);
        user.set("mobile", mobile);
        user.update();

        redirect("/adminUser");
    }

    @Before(POST.class)
    public void adminLoginAction() {
        String uname = getPara("username");
        String pwd = MD5Util.MD5(getPara("password"));

        List<User> users = User.dao.find("select * from user where loginname=? and password=?", uname, pwd);

        if (users.size() == 0) {
            keepPara();
            setAttr("error", "用户名或密码错误");
            render("/resources/system/login.html");
        } else {
            User user = users.get(0);
            setSessionAttr("user", user);
            redirect("/adminIndex");
        }
    }






}
