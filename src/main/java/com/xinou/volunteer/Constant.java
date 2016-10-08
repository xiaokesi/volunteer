package com.xinou.volunteer;

/**
 * Created by zhangbo on 15/12/25.
 *
 */
public class Constant {

    public static final String BASE_PARA = "volunteer";

    /**base**/
//    public static final String BASE_URL = "http://123.57.214.189/volunteer/";
    /**0.2.0 test**/
    public static final String BASE_URL = "http://123.206.43.110/volunteer/";



    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //获取素材列表
    public static String get_source_url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";

    public static String get_source_size_url = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";

    //获得永久素材
    public static String get_material_url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
    //图片文件下载
    public static String download_file = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=";
}
