package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.Dictionary;

import java.util.List;
/**
 * 内容管理平台字典模块持久层
 * Created by Administrator on 2016/8/31.
 */
public interface DictionaryMapper {
    int deleteByPrimaryKey(Integer dictId);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    /**
     * 查询字典数据
     * @return 字典数据列表
     */
    List<Dictionary> selectDictionary();

    Dictionary selectByPrimaryKey(Integer dictId);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
}