package com.xinou.volunteer.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.xinou.volunteer.dao.Activities;
import com.xinou.volunteer.dao.User;
import com.xinou.volunteer.utils.IdUtil;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 活动操作
 * Created by shizhida on 16/1/13.
 */
public class ActivitiesController extends Controller {

    @Before({POST.class})
    public void launchActivities() {

        //String saveDirectory = "./upload";
       // UploadFile file = getFile("pic");

        //System.out.print("file:" + file.getFile() + "\nfileName:" + file.getFileName() + "\n");

        Activities activities = new Activities();
        String id = IdUtil.getTimeMillisSequence();
        activities.put("id", id);
        activities.put("title", getPara("title"));
        activities.put("require", getPara("require"));
        activities.put("time", getPara("time"));
        activities.put("size", getPara("size"));
        activities.put("organizer", getPara("organizer"));
        activities.put("context", getPara("context"));
        activities.put("num", 0);
        activities.put("activitiestime", getPara("activitiestime"));
        activities.put("type", getPara("type"));
        //activities.put("cover", "/"+ Constant.BASE_PARA+"/upload/"+file.getFileName());
        activities.put("starttime", getPara("starttime"));
        activities.put("endtime", getPara("endtime"));
        System.out.print("title:" + getRequest().getParameter("title") + "\ntype:" + getRequest().getParameter("type") + "\n");

        String stime = activities.getStr("starttime").replace("T"," ");
        String etime = activities.getStr("endtime").replace("T"," ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        long starttime = 0;
        long endtime = 0;
        try {
            starttime = sdf.parse(stime).getTime();
            endtime = sdf.parse(etime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        activities.put("starttimet",new Timestamp(starttime));
        activities.put("endtimet", new Timestamp(endtime));

        boolean b = activities.save();
        if (!b) {
            render("/resources/launch_error.html");
        } else {
            System.out.print("上传文件成功! position:");
            render("/resources/launch_ok.html");
        }
    }

    public void launchjsp() {
        render("/resources/activities_launch.html");
    }

    public void activitiesDetailsjsp() {
        render("/resources/activities_details.html");
    }


    public void activitiesList() {
        setAttr("user_count", User.dao.find("select count(*) as num from user").get(0).get("num"));
        render("/resources/activities_type.html");
    }

    public void findAll() {
        List<Activities> list = Activities.dao.find("select * from activities order by id desc");
        renderJson(list);
    }

    public void findActivitiesById() {
        renderJson(Activities.dao.findById(getPara("id")));
    }

    public void findActivitiesByType() {
        List<Activities> list = Activities.dao.find("select * from activities where type=? order by id desc", getPara("type"));
        renderJson(list);
    }

    public void findAllList() {

        String type = getPara("type");
        String type_text ;
        switch(type){
            case "pinganshequ":
                type_text="平安社区";
                break;
            case "wenmingchuxing":
                type_text="文明出行";
                break;
            case "lvsehuajnbao":
                type_text="绿色环保";
                break;
            case "weishengjiankang":
                type_text="卫生健康";
                break;
            case "weilaozhucan":
                type_text="为老助残";
                break;
            case "wenhuayishu":
                type_text="文化艺术";
                break;
            case "jiaoxuefudao":
                type_text="教学辅导";
                break;
            case "tiyujingsai":
                type_text="体育竞赛";
                break;
            case "qita":
                type_text="其他";
                break;
            default:
                type_text="";
                break;
        }

        List<Activities> list = Activities.dao.find("select * from activities where type=? order by id desc", type_text);

        for (Activities activities : list) {
            long starttime = activities.getTimestamp("starttimet").getTime();
            long endtime = activities.getTimestamp("endtimet").getTime();
            long timestamp = System.currentTimeMillis();
            if(timestamp<starttime)
                activities.put("status","未开始");
            else if(starttime<=timestamp&&timestamp<=endtime)
                activities.put("status","活动进行中");
            else activities.put("status","已结束");
        }
        setAttr("activities", list);

        renderFreeMarker("/resources/activities.html");
    }

    /**
     * 根据id下载活动封面
     */
    public void downloadCoverById(){
        renderJson(Activities.dao.find("select cover from activities where id=?", getPara("id")));
    }


}
