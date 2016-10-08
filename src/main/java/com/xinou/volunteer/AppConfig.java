package com.xinou.volunteer;

import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.xinou.volunteer.controller.*;
import com.xinou.volunteer.dao.Activities;
import com.xinou.volunteer.dao.Organization;
import com.xinou.volunteer.dao.User;
import com.xinou.volunteer.dao.UserActivities;

/**
 * JFinal的基本配置类
 * Created by shizhida on 16/1/13.
 */
public class AppConfig extends JFinalConfig {

    /**
     * 常量配置
     * @param me
     */
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        me.setViewType(ViewType.FREE_MARKER);

        loadProp("config_pro.prop", "config.prop");
        me.setDevMode(PropKit.getBoolean("devMode", false));

        // ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
        ApiConfigKit.setDevMode(me.getDevMode());

    }

    /**
     * url映射配置
     * @param me
     */
    @Override
    public void configRoute(Routes me) {

        me.add("/activities", ActivitiesController.class);
        me.add("/organization", OrganizationController.class);
        me.add("/wechat", WechatController.class);
        me.add("/admin", AdminController.class);
        me.add("/user", UserController.class);
        me.add("/",SystemController.class);
//        me.add("/file", UploadFileController.class);
    }

    /**
     * 数据源等插件配置
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        //DruidPlugin druidPlugin = new DruidPlugin("jdbc:mysql://123.57.214.189/volunteer?useUnicode=true&characterEncoding=UTF-8","shi","");
        DruidPlugin druidPlugin = new DruidPlugin("jdbc:mysql://578d7e0e5ca35.bj.cdb.myqcloud.com:3449/volunteer?useUnicode=true&characterEncoding=UTF-8","cdb_outerroot","1qaz@2wsx");
        me.add(druidPlugin);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.addMapping("activities", Activities.class);
        arp.addMapping("user",User.class);
        arp.addMapping("user_activities", UserActivities.class);
        arp.addMapping("organization", Organization.class);
        me.add(arp);

    }

    /**
     * 拦截器配置
     * @param me
     */
    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }


    /**
     * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
     * @param pro 生产环境配置文件
     * @param dev 开发环境配置文件
     */
    public void loadProp(String pro, String dev) {
        try {
            PropKit.use(pro);
        }
        catch (Exception e) {
            PropKit.use(dev);
        }
    }


}
