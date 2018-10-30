package com.nanfang.robot.controller;

import com.nanfang.robot.util.APIClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yzx
 */
@Controller
public class RobotController {
    private static final String SECRET_ID = "bff22dae2936490b813f287a6991ed30";
    private static final String SECRET_KEY = "7a8dfe0190014700bf07f63b444fc12d";

    @RequestMapping("/hotArticle")
    public String hotArticle() {
        return "hot_article";
    }

    /**
     * 获取热门资讯
     */
    @RequestMapping("/recommender/hot_article")
    @ResponseBody
    public String hotArticle(HttpServletRequest request) {
        try {
            String field = request.getParameter("field");
            Map<String, String> params = new HashMap<>(16);
            params.put("field", field);
            String result = APIClient.post("http://api.giiso.ai/openapi/recommender/hot_article", SECRET_ID,
                    SECRET_KEY, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    @RequestMapping("/pageToKeyword")
    public String pageToKeyword(){
        return "keyword";
    }

    /**
     * 添加关键词
     */
    @RequestMapping("/keyword/add")
    public void keywordAdd(String keyword, String customId, HttpServletResponse response) {
        try {
            Map<String, String> params = new HashMap<>(16);
            params.put("customId", "15914455733");
            params.put("keyword", keyword);
            String result = APIClient.post("http://api.giiso.ai/openapi/subscribe/keyword/add", SECRET_ID,
                    SECRET_KEY, params);
            response.sendRedirect("/pageToKeyword");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除关键词
     */
    @RequestMapping("/keyword/del")
    @ResponseBody
    public String keywordDel(String keyword,String customId) {
        try {
            Map<String, String> params = new HashMap<>(16);

            params.put("customId", customId);
            params.put("keyword", keyword);

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
    @RequestMapping("/keyword/list")
    @ResponseBody
    public String keywordList(String customId) {
        try {
            Map<String, String> params = new HashMap<>(16);

            params.put("customId", customId);

            String result = APIClient.post("http://api.giiso.ai/openapi/subscribe/keyword/list", SECRET_ID,
                    SECRET_KEY, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("/subscribe/news")
    @ResponseBody
    public String subscribeNews(String keyword,String customId) {
        try {
            Map<String, String> params = new HashMap<>(16);

            params.put("customId", customId);
            params.put("keyword", keyword);

            String result = APIClient.post("http://api.giiso.ai/openapi/subscribe/news", SECRET_ID,
                    SECRET_KEY, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("/pageToSubscribeNews")
    public String pageToSubscribeNews(ModelMap map, String keyword, String customId) {
        map.addAttribute("keyword",keyword);
        map.addAttribute("customId",customId);
        return "subscribe_news";
    }
}
