package com.kuwo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kuwo.entity.Music;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

public class SearchMusicController {
    @RequestMapping("/searchMusic")
    public @ResponseBody
    List<Music> searchMusic(String key) throws IOException {
        //定义出歌曲搜索的url地址
        String searchUrl = "http://www.kuwo.cn/api/www/search/searchMusicBykeyWord?key=" + key + "&pn=1&rn=20&httpsStatus=1&reqId=ea697cc1-bb46-11ed-bcfb-f774561b7a6a";


        //去访问酷我的官网
        Connection.Response execute = Jsoup.connect("http://www.kuwo.cn").execute();
        //去拿到kw_token令牌
        String kw_token = execute.cookie("kw_token");

        //我们就可以去访问searchUrl地址了
        Document document = Jsoup.connect(searchUrl)
                .cookie("kw_token", kw_token)
                .header("csrf", kw_token)
                .header("Referer", "http://www.kuwo.cn/search/list")
                .ignoreContentType(true) //忽略数据类型
                .get();

        //处理一下我们的document文档对象
        String data = JSONObject.parseObject(document.text()).getString("data");
        //从data中取出list中的数据
        String list = JSONObject.parseObject(data).getString("list");
        //将list转换为List集合完成数据的封装
        List<Music> musics = JSONArray.parseArray(list, Music.class);

        //将集合数据返回给前端
        return musics;
    }
}
