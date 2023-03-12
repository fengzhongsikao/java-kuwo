package com.kuwo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kuwo.entity.Lyric;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LyricController {

    //根据歌曲的rid来获取对应的歌词信息
    @RequestMapping("/getLyric")
    public @ResponseBody
    List<Lyric> getLyric(String rid) throws Exception{
        //定义出歌词的url
        String lyricUrl = "http://m.kuwo.cn/newh5/singles/songinfoandlrc?musicId="+rid+"&httpsStatus=1&reqId=a09ccf30-b802-11ed-9b49-91a2e3ac4cec";
        Document document = Jsoup.connect(lyricUrl).get();
        //解析文档对象document
        String data = JSONObject.parseObject(document.text()).getString("data");
        //继续从data中解析出lrcList
        String lrcList = JSONObject.parseObject(data).getString("lrclist");
        //将其封装为List结合，响应给页面
        List<Lyric> lyrics = JSONArray.parseArray(lrcList, Lyric.class);
        return lyrics;
    }
}
