package com.kuwo.controller;

import com.alibaba.fastjson.JSONObject;
import com.kuwo.entity.Music;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MusicInfoController {

    @RequestMapping("/musicInfo")
    private @ResponseBody
    Music musicInfo(String rid) throws Exception{
        //从歌词的url中我们要取出里面的songinfo的信息
        String infoUrl = "http://m.kuwo.cn/newh5/singles/songinfoandlrc?musicId="+rid+"&httpsStatus=1&reqId=b9eb05c0-ba54-11ed-93fb-eb162f864331";
        Document document = Jsoup.connect(infoUrl).get();
        //解析文档对象
        String data = JSONObject.parseObject(document.text()).getString("data");
        //再次解析data中的数据， 取出songinfo的值
        String songinfo = JSONObject.parseObject(data).getString("songinfo");
        //将songinfo的内容封装成Music实体
        Music music = JSONObject.parseObject(songinfo, Music.class);
        music.setRid(rid);//将rid的值封装到Music实体类
        return music;
    }
}
