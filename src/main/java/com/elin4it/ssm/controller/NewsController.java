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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 内容管理平台新闻模块控制层
 * Created by Administrator on 2016/8/31.
 */
@Controller
@RequestMapping("/**/news")
public class NewsController {
    @Resource
    private NewsService newsService;
    /**
     * 添加新闻
     *
     * @param request 请求添加的新闻信息
     * @return 添加成功与否信息
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addNews(HttpServletRequest request) {
        JSONResult result;
        String NewsAuthor = request.getParameter("NewsAuthor");
        String NewsContent = request.getParameter("NewsContent");
        String NewsTitle = request.getParameter("NewsTitle");
        String NewsType = request.getParameter("NewsType");
        String NewsProgram = request.getParameter("NewsProgram");
        News news = new News();
        news.setNewsAuthor(NewsAuthor);
        news.setNewsContent(NewsContent);
        news.setNewsTitle(NewsTitle);
        news.setNewsType(NewsType);
        news.setNewsProgram(NewsProgram);
        if (newsService.addNews(news)) {
            result = new JSONResult(news);
            result.setMessage("sucess");
        } else
            result = new JSONResult();
        return result;
    }

    /**
     * 根据新闻ID删除新闻
     *
     * @param request 目标新闻ID
     * @return 成功与否信息
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public JSONResult delNewsById(HttpServletRequest request) {
        JSONResult result = new JSONResult();
        int NewsId = Integer.parseInt(request.getParameter("NewsId"));
        if (!newsService.delNewsById(NewsId))
            result.setMessage("error");
        return result;
    }

    /**
     * 根据新闻ID更新新闻
     *
     * @param request 目标新闻ID
     * @return 成功与否信息
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public JSONResult updateNews(HttpServletRequest request) {
        JSONResult result;
        int NewsId = Integer.parseInt(request.getParameter("NewsId"));
        String NewsAuthor = request.getParameter("NewsAuthor");
        String NewsContent = request.getParameter("NewsContent");
        String NewsTitle = request.getParameter("NewsTitle");
        String NewsType = request.getParameter("NewsType");
        String NewsProgram = request.getParameter("NewsProgram");
        News news = new News();
        news.setNewsId(NewsId);
        news.setNewsAuthor(NewsAuthor);
        news.setNewsContent(NewsContent);
        news.setNewsTitle(NewsTitle);
        news.setNewsType(NewsType);
        news.setNewsProgram(NewsProgram);
        if (newsService.updateNews(news)) {
            result = new JSONResult(news);
            result.setMessage("success");
        } else {
            result = new JSONResult();
            result.setMessage("error");
        }
        return result;
    }

    /**
     * 根据新闻ID单条查询新闻
     *
     * @param request 目标新闻ID
     * @return 目标新闻信息
     */
    @RequestMapping(value = "/findNewsById", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findNewsById(HttpServletRequest request) {
        JSONResult result;
        int NewsId = Integer.parseInt(request.getParameter("NewsId"));
        News news = newsService.findNewsById(NewsId);
        result = new JSONResult(news);
        return result;
    }

    /**
     * 根据新闻栏目查询新闻
     *
     * @param request 新闻栏目
     * @return 新闻栏目对应的新闻列表
     */
    @RequestMapping(value = "/findNewsByProgram", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findNewsByProgram(HttpServletRequest request) {
        String NewsProgram = request.getParameter("NewsProgram");
        List<News> news = newsService.findNewsByNewsProgram(NewsProgram);
        return new JSONResult(news);
    }

    /**
     * 根据新闻类型查询新闻
     * @param request 新闻类型
     * @return 新闻类型对应的新闻列表
     */
    @RequestMapping(value = "/findNewsByType", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findNewsByType(HttpServletRequest request) {
        String NewsType = request.getParameter("NewsType");
        List<News> news = newsService.findNewsByNewsType(NewsType);
        return new JSONResult(news);
    }

@RequestMapping(value = "/findNewsByTitle",method = RequestMethod.GET)
@ResponseBody
public JSONResult findNewsByTitle(HttpServletRequest request) throws UnsupportedEncodingException {
    String NewsTitle = URLDecoder.decode(request.getParameter("newsTitle"), "UTF-8");
    List<News> news=newsService.findNewsByNewsTitle(NewsTitle);
    return  new JSONResult(news);
}
    /**
     * 查询所有新闻
     *
     * @return 所有新闻列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findAllNews() {
        List<News> news = newsService.findAllNews();
        return new JSONResult(news);
    }

    /**
     * 根据新闻ID查询新闻详情
     *
     * @param request 新闻ID
     * @return 目标新闻详情
     */
    @RequestMapping(value = "/findDetail", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findDetail(HttpServletRequest request) {
        int NewsId = Integer.parseInt(request.getParameter("NewsId"));
        String newsContent = newsService.findDetailById(NewsId);
        return new JSONResult(newsContent);
    }


    /**
     * 发布所有未发布的新闻
     * @return 成功与否信息
     */
    @RequestMapping(value = "/publishNews", method = RequestMethod.PUT)
    @ResponseBody
    public JSONResult publishAllNews() {
        JSONResult result = new JSONResult();
        List<News> news = newsService.findAllNews();
        for (News news1 : news) {
            if (news1.getNewsStatus().equals("0")) {
                news1.setNewsStatus("1");
                newsService.updateNews(news1);
            }
        }
        result.setMessage("success");
        return result;
    }
}