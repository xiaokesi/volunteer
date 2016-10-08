package com.xinou.volunteer.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
/**
 * Created by zhangbo on 16/1/20.
 */
public class ActivitiesLaunchValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        validateRequiredString("title", "titleMsg", "必须输入项目名称!");
        validateRequiredString("require", "requireMsg", "必须输入志愿者要求!");
        validateRequiredString("size", "sizeMsg", "必须输入计划招募人数!");
        validateRequiredString("organizer", "organizerMsg", "必须输入活动组织者!");
        validateRequiredString("context", "contextMsg", "必须输入项目介绍!");
        validateRequiredString("activitiestime", "activitiestimeMsg", "必须输入活动时间!");
        validateRequiredString("type", "typeMsg", "必须输入活动类型!");
        validateRequiredString("time", "timeMsg", "必须输入报名截止日期!");
    }

    @Override
    protected void handleError(Controller c) {
        c.keepPara();
        String str = c.getAttrForStr("error");
        System.out.println(str);
        c.renderFreeMarker("/resources/activities_launch.html");
    }
}
