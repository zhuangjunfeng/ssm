package com.elin4it.ssm.controller;

import com.elin4it.ssm.pojo.News;
import com.elin4it.ssm.service.NewsService;
import com.elin4it.ssm.util.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
@Controller
@RequestMapping("/news")
public class NewsContorller {
    @Resource
    private NewsService newsService;

    /**
     * @deprecation:新增新闻
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addNews(HttpServletRequest request){
        JSONResult result;
        String newsType = request.getParameter("newsType");
        String newsTitle = request.getParameter("newsTitle");
        String newsContent = request.getParameter("newsContent");
        String newsAuthor=request.getParameter("newsAuthor");
        News news=new News();
        news.setNewsAuthor(newsAuthor);
        news.setNewsContent(newsContent);
        news.setNewsTitle(newsTitle);
        news.setNewsType(newsType);
        if (newsService.addNews(news))
            {
                result=new JSONResult(news);
                result.setMessage("sucess");
            }
        else
                result=new JSONResult();
        return result;
    }

    /**
     * 
     * @deprecation:通过id删除新闻
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public JSONResult delNewsById(HttpServletRequest request){
        JSONResult result=new JSONResult();
        int newsId=Integer.parseInt(request.getParameter("newsId"));
        if (!newsService.delNewsById(newsId))
            result.setMessage("error");
        return result;
    }

    /**
     * @deprecation:通过id更新新闻
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public JSONResult updateNews(HttpServletRequest request)
    {
        JSONResult result;
        int newsId=Integer.parseInt(request.getParameter("newsId"));
        String newsType = request.getParameter("newsType");
        String newsTitle = request.getParameter("newsTitle");
        String newsContent = request.getParameter("newsContent");
        String newsAuthor=request.getParameter("newsAuthor");
        News news=new News();
        news.setNewsId(newsId);
        news.setNewsAuthor(newsAuthor);
        news.setNewsContent(newsContent);
        news.setNewsTitle(newsTitle);
        news.setNewsType(newsType);
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

    /**
     * @deprecation:通过id查询新闻
     * @return
     */
    @RequestMapping(value = "/findNewsById",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findNewsById(HttpServletRequest request){
        JSONResult result;
        int newsId=Integer.parseInt(request.getParameter("newsId"));
        News news=newsService.findNewsById(newsId);
        result=new JSONResult(news);
        return result;
    }

    /**
     * @deprecation:查询全部新闻
     * @return：全部新闻列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findAllNews(){
        List<News> news=newsService.findAllNews();
        return new JSONResult(news);
    }
}
