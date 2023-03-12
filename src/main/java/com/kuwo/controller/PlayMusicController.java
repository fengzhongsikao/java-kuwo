package com.kuwo.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PlayMusicController {
    //根据歌曲的rid来帮我们获取到音乐的播放地址
    @RequestMapping("/playMusic")
    public @ResponseBody
    String playMusic(String rid) throws Exception{
        //定义出歌曲的播放地址
        String playUrl = "http://antiserver.kuwo.cn/anti.s?type=convert_url&rid=MUSIC_"+rid+"&format=mp3|mp3&response=url";
        //根据上面的playUrl地址帮我们解析出歌曲的真正的播放地址
        Document document = Jsoup.connect(playUrl).get();
        //直接解析document文档对象，拿到歌曲真正的播放地址
        String mp3Url = document.text();
        return mp3Url;
    }
}
