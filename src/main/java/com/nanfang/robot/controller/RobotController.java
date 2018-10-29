package com.nanfang.robot.controller;

import com.nanfang.robot.util.APIClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yzx
 */
@Controller
public class RobotController {
    private static final String SECRET_ID = "bff22dae2936490b813f287a6991ed30";
    private static final String SECRET_KEY = "7a8dfe0190014700bf07f63b444fc12d";
    @RequestMapping("/hello")
    public String helloHtml(HashMap<String,Object> map){
        map.put("hello", "欢迎进入HTML页面");
        return "/index";
    }
    /**
     * 获取热门资讯
     */
    @RequestMapping("/recommender/hot_article")
    public String hotArticle(HttpServletRequest request){
        try {
            Map<String, String> params = new HashMap<>(16);
            params.put("field", "体育");
            String result = APIClient.post("http://api.giiso.ai/openapi/recommender/hot_article", SECRET_ID,
                    SECRET_KEY, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 添加关键词
     */
    public String keywordAdd(){
        try {
            Map<String, String> params = new HashMap<>(16);
            params.put("customId", "");
            params.put("keyword", "");
            String result = APIClient.post("http://api.giiso.ai/openapi/subscribe/keyword/add", SECRET_ID,
                    SECRET_KEY, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 删除关键词
     */
    public String keywordDel(){
        try {
            Map<String, String> params = new HashMap<>(16);

            params.put("customId", "");
            params.put("keyword", "");

            String result = APIClient.post("http://api.giiso.ai/openapi/subscribe/keyword/del", SECRET_ID,
                    SECRET_KEY, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取关键词列表
     */
    public String keywordList(){
        try {
            Map<String, String> params = new HashMap<>(16);

            params.put("customId", "");

            String result = APIClient.post("http://api.giiso.ai/openapi/subscribe/keyword/list", SECRET_ID,
                    SECRET_KEY, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
