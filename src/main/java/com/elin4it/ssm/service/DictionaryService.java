package com.elin4it.ssm.service;

import com.elin4it.ssm.pojo.Dictionary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容管理平台字典模块业务层接口
 * Created by Administrator on 2016/9/1.
 */
@Service
public interface DictionaryService {
    /**
     * 查找字典数据
     * @return 字典数据列表
     */
    List<Dictionary> findDictionary();
}
