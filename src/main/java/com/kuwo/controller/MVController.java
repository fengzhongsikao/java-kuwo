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
public class MVController {
    //跳转到mv的页面
    @RequestMapping("/mv")
    public String toMv(){
        return "mv";
    }



    //根据歌曲rid来获取mv的地址信息
    @RequestMapping("/getMVUrl")
    public @ResponseBody  String getMVUrl(String rid) throws Exception{
        //定义出mv的url地址
        String mvUrl = "http://www.kuwo.cn/api/v1/www/music/playUrl?mid="+rid+"&type=mv&httpsStatus=1&reqId=4a636a90-b805-11ed-ae7c-c749f3e203aa";
        Document document = Jsoup.connect(mvUrl).ignoreContentType(true).get();
        //解析文档对象
        String data = JSONObject.parseObject(document.text()).getString("data");
        //继续解析data,取出url
        String url = JSONObject.parseObject(data).getString("url");
        return url;
    }

    //获取歌曲MV的评论信息
    @RequestMapping("/getMVComment")
    public  @ResponseBody  List<Comment> getMVComment(String rid) throws Exception{
        //定义出mv评论的url
        String mvCommentUrl = "http://www.kuwo.cn/comment?type=get_rec_comment&f=web&page=1&rows=30&digest=7&sid="+rid+"&uid=0&prod=newWeb&httpsStatus=1&reqId=e3360930-b805-11ed-8135-d901b74c702f";
        Document document = Jsoup.connect(mvCommentUrl).get();
        //解析文档对象document
        String rows = JSONObject.parseObject(document.text()).getString("rows");
        //封装成Comment对象
        List<Comment> comments = JSONArray.parseArray(rows, Comment.class);
        return comments;
    }
    @RequestMapping("/getMVhotComment")
    public  @ResponseBody  List<Comment> getMVhotComment(String rid) throws Exception{
        //定义出mv评论的url
        String mvCommentUrl = " http://www.kuwo.cn/comment?type=get_comment&f=web&page=1&rows=5&digest=7&sid="+rid+"&uid=0&prod=newWeb&httpsStatus=1&reqId=990ff870-bb4d-11ed-a507-ef89eb01af76";
        Document document = Jsoup.connect(mvCommentUrl).get();
        //解析文档对象document
        String rows = JSONObject.parseObject(document.text()).getString("rows");
        //封装成Comment对象
        List<Comment> comments = JSONArray.parseArray(rows, Comment.class);
        return comments;
    }
}
