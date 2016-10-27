package com.elin4it.ssm.service;

import com.elin4it.ssm.pojo.News;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容管理平台字典模块业务层接口
 * Created by Administrator on 2016/8/31.
 */
@Service
public interface NewsService {
    /**
     * 增加新闻
     * @param news 添加的新闻信息
     * @return 是否成功
     */
    boolean addNews(News news);

    /**
     * 根据ID删除新闻
     * @param newsId 新闻ID
     * @return 是否成功
     */
    boolean delNewsById(Integer newsId);

    /**
     * 更新新闻
     * @param news 更新新闻的信息
     * @return 是否成功
     */
    boolean updateNews(News news);

    /**
     * 根据ID查询新闻
     * @param newsId 新闻ID
     * @return 目标新闻信息
     */
    News findNewsById(Integer newsId);

    /**
     * 查询所有新闻
     * @return 所有新闻列表
     */
    List<News> findAllNews();

    /**
     * 根据ID查询新闻详情
     * @param newsId 新闻ID
     * @return 目标新闻内容
     */
    String findDetailById(Integer newsId);

    /**
     * 根据新闻栏目查询新闻
     * @param newsProgram 新闻栏目
     * @return 对应新闻列表
     */
    List<News> findNewsByNewsProgram(String newsProgram);

    /**
     * 根据新闻类型查询新闻
     * @param newsType 新闻类型
     * @return 对应新闻列表
     */
    List<News> findNewsByNewsType(String newsType);
    List<News> findNewsByNewsTitle(String newsTitle);
}
