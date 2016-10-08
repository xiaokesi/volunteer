package com.xinou.volunteer.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * 用户注册信息校验
 * Created by shizhida on 16/1/19.
 */
public class RegisterValidator extends Validator {

    @Override
    protected void validate(Controller c) {

//        System.out.println(c.getParaMap());
        validateRequiredString("loginname", "error", "必须输入用户名");
        validateEmail("email", "error", "请输入正确的email地址");
        validateRegex("password", "^[a-zA-Z]\\w{5,17}$", "error", "请输入合法的密码（6-18位以字母开头的字母或数字）");
        validateEqualField("password","repassword","error","两次输入的密码不相同");
        validateRequiredString("realname", "error", "必须输入用户名");
        validateRegex("idcard", "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)", "error", "请输入合法的身份证号");


    }

    @Override
    protected void handleError(Controller c) {

        c.keepPara();
        String str = c.getAttrForStr("error");
        System.out.println(str);
        c.renderFreeMarker("/resources/user/register.html");


    }
}
