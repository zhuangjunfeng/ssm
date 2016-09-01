package com.elin4it.ssm.mapper;

import com.elin4it.ssm.pojo.Dictionary;
import java.util.List;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Integer dictId);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);
    List<Dictionary> selectDictionary();
    Dictionary selectByPrimaryKey(Integer dictId);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
}