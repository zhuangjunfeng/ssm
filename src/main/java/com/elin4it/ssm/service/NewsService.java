package com.elin4it.ssm.service;

import com.elin4it.ssm.pojo.News;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
@Service
public interface NewsService {
    boolean addNews(News news);
    boolean delNewsById(Integer newsId);
    boolean updateNews(News news);
    News findNewsById(Integer newsId);
    List<News> findAllNews();
    String findDetailById(Integer newsId);
    List<News> findNewsByNewsProgram(String newsProgram);
    List<News> findNewsByNewsType(String newsType);
}
