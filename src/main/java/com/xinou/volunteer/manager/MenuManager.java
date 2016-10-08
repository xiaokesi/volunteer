package com.xinou.volunteer.manager;

import com.jfinal.weixin.sdk.api.*;
import com.jfinal.weixin.sdk.utils.JsonUtils;
import com.xinou.volunteer.Constant;
import com.xinou.volunteer.pojo.Button;
import com.xinou.volunteer.pojo.CommonButton;
import com.xinou.volunteer.pojo.ComplexButton;
import com.xinou.volunteer.pojo.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 *
 * @author zhangbo
 */
public class MenuManager {
    private static Logger log = LoggerFactory.getLogger(MenuManager.class);

    public static void main(String[] args) {

        ApiConfigKit.setThreadLocalApiConfig(getApiConfig());
        // 调用接口创建菜单
        ApiResult result = MenuApi.createMenu(getMenu());

        // 判断菜单创建结果
        if (result.isSucceed())
            log.info("菜单创建成功！" + AccessTokenApi.getAccessTokenStr());
        else
            log.info("菜单创建失败，错误码：" + result);
    }

    /**
     * 组装菜单数据
     *
     * @return
     */
    private static String getMenu() {

        /**
         * 项目发布(公益活动)
         * 纯文字
         * 标题 内容 时间 地点 发布人 人数
         * 移动端发送 后台也发
         */
        CommonButton btn11 = new CommonButton();
        btn11.setName("志愿项目发布");
        btn11.setType("view");
        btn11.setUrl(Constant.BASE_URL + "activities/launchjsp");
//        btn11.setUrl("http://www.bv2008.cn/app/opp/opp.edit.php");

        /**
         * 品牌活动(活动展示)
         * 纯文字
         * 活动列表 跳到网页
         * 列表能点进活动详情页
         * 活动详情页纯文字
         */
        CommonButton btn12 = new CommonButton();
        btn12.setName("志愿项目报名");
        btn12.setType("view");
        btn12.setUrl(Constant.BASE_URL + "activities/activitiesList");
//        btn12.setUrl("http://bjdx.zhiyuanyun.com/app/weixin/near.php?type=opp_list");

        /**
         * 主题宣传(文章报道)
         * 图文混排
         * 标题 内容 报道人 报到时间
         */
        CommonButton btn13 = new CommonButton();
        btn13.setName("主题宣传");
        btn13.setType("click");
        btn13.setKey("13");

        /**
         * 图文混排
         */
        CommonButton btn14 = new CommonButton();
        btn14.setName("志愿者风采");
        btn14.setType("click");
        btn14.setKey("14");


        CommonButton btn15 = new CommonButton();
        btn15.setName("微社区");
        btn15.setType("view");
        btn15.setUrl("http://buluo.qq.com/mobile/barindex.html?_bid=128&_wv=1027&bid=292729");


        /**
         * 图文混排
         *
         */
        CommonButton btn21 = new CommonButton();
        btn21.setName("课程纵览");
        btn21.setType("click");
        btn21.setKey("21");


        /**
         * 一个静态网页文章
         */
        CommonButton btn22 = new CommonButton();
        btn22.setName("师资团队");
        btn22.setType("click");
        btn22.setKey("22");

        /**
         * 图文混排
         *
         */
        CommonButton btn23 = new CommonButton();
        btn23.setName("品牌宣传");
        btn23.setType("click");
        btn23.setKey("23");

        CommonButton btn31 = new CommonButton();
        btn31.setName("登录");
        btn31.setType("view");
        btn31.setUrl(Constant.BASE_URL +"user/loginPage");

        /**
         * 直接跳他们的注册页面
         */
        CommonButton btn32 = new CommonButton();
        btn32.setName("志愿者登录注册");
        btn32.setType("view");
        //btn32.setKey("32");
//        btn31.setUrl("http://www.bv2008.cn/app/user/register.php");
        btn32.setUrl(Constant.BASE_URL +"user/registerPage");

//        /**
//         * 同上
//         */
//        CommonButton btn33 = new CommonButton();
//        btn33.setName("志愿团体登录注册");
//        btn33.setType("view");
//        //btn33.setKey("33");
//        btn33.setUrl(Constant.BASE_URL +"user/registerPage");
////        btn32.setUrl("http://www.bv2008.cn/app/user/register.php?type=org");


        /**
         * 组织机构展示
         */
        CommonButton btn34 = new CommonButton();
//        btn33.setName("附近的志愿者");
        btn34.setName("组织机构");
        btn34.setType("view");
        btn34.setUrl(Constant.BASE_URL + "organization/getOrganization");
//        btn33.setUrl("http://bjdx.zhiyuanyun.com/app/weixin/near.php?m=list&type=vol_list");

//        /**
//         * 弹出几个字
//         */
//        CommonButton btn35 = new CommonButton();
//        btn35.setName("志愿团体加入");
//        btn35.setType("view");
//        btn35.setUrl("http://bjdx.zhiyuanyun.com/app/weixin/near.php?m=list&type=org_list");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("亦活动");
        mainBtn1.setSub_button(new CommonButton[]{btn11, btn12, btn13, btn14, btn15});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("亦学堂");
        mainBtn2.setSub_button(new CommonButton[]{btn21, btn22, btn23});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("加入我们");
        mainBtn3.setSub_button(new CommonButton[]{btn31, btn32, btn34});

        /**
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
         *
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
         */
        Menu menu = new Menu();
        menu.setButton(new Button[]{mainBtn1, mainBtn2, mainBtn3});
        //System.out.print(JsonUtils.toJson(menu));
        return JsonUtils.toJson(menu);
    }

    public static ApiConfig getApiConfig() {
        ApiConfig ac = new ApiConfig();


        // 配置微信 API 相关常量
        ac.setToken("volunteer");
        ac.setAppId("wx0f4b9430f14e445a");
        ac.setAppSecret("b6ddf54b13075ed36d871df4e8897d71");

        /**
         *  是否对消息进行加密，对应于微信平台的消息加解密方式：
         *  1：true进行加密且必须配置 encodingAesKey
         *  2：false采用明文模式，同时也支持混合模式
         */
        ac.setEncryptMessage(false);
        ac.setEncodingAesKey("ua6IwkzhDTlo1hgMLkr6HpcvVHyUsmu5DnIRb5DZw2j");
        return ac;
    }


}
