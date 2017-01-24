package com.ssm.service.impl;

import com.ssm.mapper.NewsMapper;
import com.ssm.pojo.News;
import com.ssm.service.NewsService;
import com.ssm.mapper.NewsMapper;
import com.ssm.pojo.News;
import com.ssm.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内容管理平台新闻模块业务层实现类
 * Created by Administrator on 2016/8/31.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsMapper;

    /**
     * 添加新闻
     *
     * @param news 添加的新闻信息
     * @return 是否成功
     */
    public boolean addNews(News news) {
        if (newsMapper.insert(news))
            return true;
        return false;
    }

    /**
     * 根据新闻ID删除新闻
     *
     * @param newsId 新闻ID
     * @return 是否成功
     */
    public boolean delNewsById(Integer newsId) {
        if (newsMapper.deleteByPrimaryKey(newsId))
            return true;
        return false;
    }

    /**
     * 更新新闻
     *
     * @param news 更新新闻的信息
     * @return 是否成功
     */
    public boolean updateNews(News news) {
        if (newsMapper.updateByPrimaryKeySelective(news))
            return true;
        else
            return false;
    }

    /**
     * 根据新闻ID查询新闻
     *
     * @param NewsId 新闻ID
     * @return 目标新闻信息
     */
    public News findNewsById(Integer NewsId) {
        return newsMapper.selectByPrimaryKey(NewsId);
    }

    /**
     * 查询全部新闻
     *
     * @return 全部新闻列表
     */
    public List<News> findAllNews() {
        List<News> news = newsMapper.selectAllNews();
        return news;
    }

    /**
     * 根据新闻ID删除新闻
     *
     * @param NewsId 新闻ID
     * @return 是否成功
     */
    public String findDetailById(Integer NewsId) {
        return newsMapper.selectDetailByPrimaryKey(NewsId);
    }

    /**
     * 根据新闻栏目查询新闻
     *
     * @param newsProgram 新闻栏目
     * @return 对应新闻列表
     */
    public List<News> findNewsByNewsProgram(String newsProgram) {
        List<News> news = newsMapper.selectNewsByNewsProgram(newsProgram);
        return news;
    }

    /**
     * 根据新闻类型查询新闻
     *
     * @param newsType 新闻类型
     * @return 对应新闻列表
     */
    public List<News> findNewsByNewsType(String newsType) {
        List<News> news = newsMapper.selectNewsByNewsType(newsType);
        return news;
    }

    /**
     * 根据新闻标题查询新闻
     * @param newsTitle 新闻标题
     * @return 符合条件的新闻列表
     */
    public List<News> findNewsByNewsTitle(String newsTitle) {
        List<News> news = newsMapper.selectNewsByNewsTitle(newsTitle);
        return news;
    }

    /**
     * 分页查询新闻
     * @param PageNo 页码
     * @param PageSize 每页记录条数
     * @return 新闻列表
     */
    public List<News> findNews(int PageNo, int PageSize) {
        int start = 0;
        start = (PageNo - 1) * PageSize;
        return newsMapper.selectNews(start, PageSize);
    }
    public String count(){
        return newsMapper.count();
    }
}

