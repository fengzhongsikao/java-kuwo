package com.kuwo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kuwo.entity.Comment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommentController {

    //跳转评论页面
    @RequestMapping("/comment")
    public String comment(){
        return "comment";
    }

    //根据rid来获取歌曲的最新评论
    @RequestMapping("/normalComment")
    public @ResponseBody List<Comment> getComment(String rid) throws Exception{
        //定义出最新评论的url
        String normalUrl = "http://www.kuwo.cn/comment?type=get_comment&f=web&page=1&rows=15&digest=15&sid="+rid+"&uid=0&prod=newWeb&httpsStatus=1&reqId=6538fae0-b7da-11ed-9ed2-f7ebd8615b65";
        Document document = Jsoup.connect(normalUrl).get();
        //解析文档对象，取出里面的最新评论内容
        String rows = JSONObject.parseObject(document.text()).getString("rows");
        //封装成list集合
        List<Comment> comments = JSONArray.parseArray(rows, Comment.class);
        return comments;
    }

    //根据歌曲的rid来获取歌曲的热门评论
    @RequestMapping("/hotComment")
    public @ResponseBody List<Comment> getHotComment(String rid) throws Exception{
        //定义出热门评论的url
        String hotUrl = "http://www.kuwo.cn/comment?type=get_rec_comment&f=web&page=1&rows=15&digest=15&sid="+rid+"&uid=0&prod=newWeb&httpsStatus=1&reqId=65265d41-b7da-11ed-9ed2-f7ebd8615b65";
        Document document = Jsoup.connect(hotUrl).get();
        //解析文档对象，取出里面的最新评论内容
        String rows = JSONObject.parseObject(document.text()).getString("rows");
        //封装成list集合
        List<Comment> comments = JSONArray.parseArray(rows, Comment.class);
        return comments;
    }
}
