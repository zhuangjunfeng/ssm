package com.elin4it.ssm.service.impl;

import com.elin4it.ssm.mapper.DictionaryMapper;
import com.elin4it.ssm.mapper.NewsMapper;
import com.elin4it.ssm.pojo.Dictionary;
import com.elin4it.ssm.pojo.News;
import com.elin4it.ssm.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsMapper;
    @Resource
    private DictionaryMapper dictionaryMapper;
    public boolean addNews(News news){
        List<Dictionary> allDictonary=dictionaryMapper.selectDictionary();
        System.out.println(allDictonary);
        for(Dictionary check:allDictonary){
            if(check.getDictName().equals(news.getNewsType())){
                news.setNewsProgram(check.getDictFather());
            }
        }
        if (newsMapper.insert(news))
            return true;
        return false;
    }
    public boolean delNewsById(Integer newsId){
        if (newsMapper.deleteByPrimaryKey(newsId))
            return true;
        return false;
    }
    public boolean updateNews(News news){
        List<Dictionary> allDictonary=dictionaryMapper.selectDictionary();
        for(Dictionary check:allDictonary){
            if(check.getDictName().equals(news.getNewsType())){
                news.setNewsProgram(check.getDictFather());
            }
        }
        if (newsMapper.updateByPrimaryKey(news))
            return true;
        else
            return false;
    }
    public News findNewsById(Integer NewsId){
        return newsMapper.selectByPrimaryKey(NewsId);
    }
    public List<News> findAllNews(){
        List<News> news=newsMapper.selectAllNews();
        return news;
    }
   public  String findDetailById(Integer NewsId){
       return newsMapper.selectDetailByPrimaryKey(NewsId);
   }
    public  List<News> findNewsByNewsProgram(String newsProgram){
        List<News> news=newsMapper.selectNewsByNewsProgram(newsProgram);
        return news;
    }

    public  List<News> findNewsByNewsType(String newsType){
        List<News> news=newsMapper.selectNewsByNewsProgram(newsType);
        return news;
    }
}

