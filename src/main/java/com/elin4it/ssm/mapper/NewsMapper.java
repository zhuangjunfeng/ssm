package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.News;

import java.util.List;

/**
 * 内容管理平台新闻模块持久层接口
 * Created by Administrator on 2016/8/31.
 */
public interface NewsMapper {
    /**
     * 根据用户ID删除新闻
     *
     * @param newsId 新闻ID
     * @return是否成功
     */
    boolean deleteByPrimaryKey(Integer newsId);

    /**
     * 添加新闻
     *
     * @param record 添加新闻的信息
     * @return是否成功
     */
    boolean insert(News record);

    int insertSelective(News record);

    /**
     * 根据新闻ID查询新闻
     * @param newsId 新闻ID
     * @return 目标新闻信息
     */
    News selectByPrimaryKey(Integer newsId);

    /**
     * 查询全部新闻
     * @return 全部新闻列表
     */
    List<News> selectAllNews();

    int updateByPrimaryKeySelective(News record);

    /**
     * 更新新闻
     * @param record 更新新闻的信息
     * @return 是否成功
     */
    boolean updateByPrimaryKey(News record);

    /**
     * 根据新闻Id查询新闻详情
     * @param newsId 新闻ID
     * @return 目标新闻新闻详情
     */
    String selectDetailByPrimaryKey(Integer newsId);

    /**
     * 根据新闻栏目查询新闻
     * @param newsProgram 新闻栏目
     * @return 对应新闻列表
     */
    List<News> selectNewsByNewsProgram(String newsProgram);

    /**
     * 根据新闻类型查询新闻
     * @param newsType 新闻类型
     * @return 对应新闻列表
     */
    List<News> selectNewsByNewsType(String newsType);
    List<News> selectNewsByNewsTitle(String newsTitle);
}