package com.xinou.volunteer.dao;

import com.jfinal.plugin.activerecord.Model;

/**
 * 用户dao
 * Created by shizhida on 16/1/19.
 */
public class User extends Model<User> {

    public static final User dao = new User();

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


}
