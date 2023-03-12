package com.kuwo.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

@Controller
public class DownMusicController {

    //  歌手名称 - 歌曲名称.mp3    周杰伦 - 夜曲.mp3
    //下载的原理其实就是采用IO流的事项来实现
    //输入流(指的是从哪里读取目标文件) 和 输出流(指的是将文件写到哪个位置  d://music//xxxx.mp3)
    @RequestMapping("/downMusic")
    public @ResponseBody String downMusic(String rid, String artist, String name) throws Exception{
        //返回值string其实就是一个友好提示
        //定义歌曲的播放地址url
        String playUrl = "http://antiserver.kuwo.cn/anti.s?type=convert_url&rid=MUSIC_"+rid+"&format=mp3|mp3&response=url";
        //使用jsoup去访问改地址
        Document document = Jsoup.connect(playUrl).get();
        //获取到音乐真正的播放地址
        String mp3Url = document.text(); // http://xxxxxx.mp3
        //根据歌曲的播放地址得到输入流对象
        URL url = new URL(mp3Url);
        InputStream is = url.openStream();
        //创建输出流对象
        FileOutputStream fos = new FileOutputStream(new File("C:\\Kugou\\"+artist+" - "+name+".mp3"));
        //开始来进行读写操作
        byte[] bys = new byte[1024];
        int len;
        while ((len = is.read(bys))!=-1){
            fos.write(bys,0,len);
        }
        //释放资源
        fos.close();
        is.close();
        //友好提示的返回
        return "下载歌曲成功";
    }
}
