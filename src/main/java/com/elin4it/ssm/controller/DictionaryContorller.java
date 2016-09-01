package com.elin4it.ssm.controller;

import com.elin4it.ssm.util.DictionaryUtil;
import com.elin4it.ssm.util.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/1.
 */
@Controller
public class DictionaryContorller {
    private Map application;
    public List list;
    @RequestMapping("/{type}")
    @ResponseBody
    public JSONResult queryData(@PathVariable("type")String type){
        list= DictionaryUtil.queryData(type, application);
        return new JSONResult(list);
    }

}
