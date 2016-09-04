package com.elin4it.ssm.controller;

import com.elin4it.ssm.pojo.News;
import com.elin4it.ssm.service.NewsService;
import com.elin4it.ssm.util.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
@Controller
@RequestMapping("/news")
public class NewsContorller {
    @Resource
    private NewsService newsService;
    @RequestMapping(value = "/addNews/{NewsTitle}&{NewsType}&{NewsAuthor}&{NewContent}")
    @ResponseBody
    public JSONResult addNews(@PathVariable("NewsTitle")String NewsTitle,
                              @PathVariable("NewsType")String NewsType,
                              @PathVariable("NewsAuthor")String NewsAuthor,
                              @PathVariable("NewsContent")String NewsContent){
        JSONResult result;
        News news=new News();
        news.setNewsAuthor(NewsAuthor);
        news.setNewsContent(NewsContent);
        news.setNewsTitle(NewsTitle);
        news.setNewsType(NewsType);
        if (newsService.addNews(news))
            {
                result=new JSONResult(news);
                result.setMessage("sucess");
            }
        else
                result=new JSONResult();
        return result;
    }
    @RequestMapping("/delNews/{newsId}")
    @ResponseBody
    public JSONResult delNewsById(@PathVariable("NewsId")int NewsId){
        JSONResult result=new JSONResult();
        if (!newsService.delNewsById(NewsId))
            result.setMessage("error");
        return result;
    }
    @RequestMapping("/updateNews/{NewsId}&{NewsTitle}&{NewsType}&{NewsAuthor}&{NewContent}")
    @ResponseBody
    public JSONResult updateNews(@PathVariable("NewsId")int NewsId,
                                 @PathVariable("NewsTitle")String NewsTitle,
                                 @PathVariable("NewsType")String NewsType,
                                 @PathVariable("NewsAuthor")String NewsAuthor,
                                 @PathVariable("NewsContent")String NewsContent)
    {
        JSONResult result;
        News news=new News();
        news.setNewsId(NewsId);
        news.setNewsAuthor(NewsAuthor);
        news.setNewsContent(NewsContent);
        news.setNewsTitle(NewsTitle);
        news.setNewsType(NewsType);
        if (newsService.updateNews(news)){
            result=new JSONResult(news);
            result.setMessage("success");
        }
        else{
            result=new JSONResult();
            result.setMessage("error");
        }
        return result;
    }
    @RequestMapping(value = "/findNewsById/{NewsId}",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findNewsById(@PathVariable("NewsId") int NewsId){
        JSONResult result;
        News news=newsService.findNewsById(NewsId);
        result=new JSONResult(news);
        return result;
    }
    @RequestMapping("/findAllNews")
    @ResponseBody
    public JSONResult findAllNews(){
        List<News> news=newsService.findAllNews();
        return new JSONResult(news);
    }
}
