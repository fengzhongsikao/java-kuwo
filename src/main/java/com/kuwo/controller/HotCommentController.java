package com.kuwo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kuwo.entity.Comment;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class HotCommentController {

    //获取歌曲的热门评论
    @RequestMapping("/hotComment")
    public List<Comment> hotComment(String rid) throws Exception{
        //定义出歌曲热门评论的url
        String hotCommentUrl = "http://www.kuwo.cn/comment?type=get_rec_comment&f=web&page=1&rows=30&digest=15&sid="+rid+"&uid=0&prod=newWeb&httpsStatus=1&reqId=d63fee01-b803-11ed-ae7c-c749f3e203aa";
        Document document = Jsoup.connect(hotCommentUrl).get();
        //解析文档对象document
        String rows = JSONObject.parseObject(document.text()).getString("rows");
        //将热门评论内容进行封装
        List<Comment> comments = JSONArray.parseArray(rows, Comment.class);
        return comments;
    }

    //歌曲的最新评论
    @RequestMapping("/normalComment")
    public List<Comment> normalComment(String rid) throws Exception{
        //定义出歌曲最新评论的url
        String normalCommentUrl = "http://www.kuwo.cn/comment?type=get_comment&f=web&page=1&rows=30&digest=15&sid="+rid+"&uid=0&prod=newWeb&httpsStatus=1&reqId=d658a620-b803-11ed-ae7c-c749f3e203aa";
        Document document = Jsoup.connect(normalCommentUrl).get();
        //解析文档对象document
        String rows = JSONObject.parseObject(document.text()).getString("rows");
        //将热门评论内容进行封装
        List<Comment> comments = JSONArray.parseArray(rows, Comment.class);
        return comments;
    }
}
