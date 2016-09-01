package com.elin4it.ssm.service;

import com.elin4it.ssm.pojo.Dictionary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
@Service
public interface DictionaryService {
    List<Dictionary> findDictionary();
}
