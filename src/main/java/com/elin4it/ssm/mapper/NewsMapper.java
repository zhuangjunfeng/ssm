package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.News;

import java.util.List;

public interface NewsMapper {
    boolean deleteByPrimaryKey(Integer newsId);
    boolean insert(News record);
    int insertSelective(News record);
    News selectByPrimaryKey(Integer newsId);
    List<News> selectAllNews();
    int updateByPrimaryKeySelective(News record);
    boolean updateByPrimaryKey(News record);
    String selectDetailByPrimaryKey(Integer newsId);
    List<News> selectNewsByNewsProgram(String newsProgram);
    List<News> selectNewsByNewsType(String newsType);
}