package com.elin4it.ssm.service.impl;

import com.elin4it.ssm.mapper.DictionaryMapper;
import com.elin4it.ssm.pojo.Dictionary;
import com.elin4it.ssm.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内容管理平台字典模块业务层实现类
 * Created by Administrator on 2016/9/1.
 */
@Service
public class DictionaryServiceImpl implements DictionaryService{
    @Resource
    private DictionaryMapper dictionaryMapper;

    /**
     * 查询字典数据
     * @return 字典数据列表
     */
    public List<Dictionary> findDictionary() {
        return dictionaryMapper.selectDictionary();
    }
}
