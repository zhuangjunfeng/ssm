package com.elin4it.ssm.util;

import com.elin4it.ssm.pojo.Dictionary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
@Controller
public class DictionaryUtil {
    private final static String DICTIONARY_CACHE = "dictionary";
    @RequestMapping("/findDic/{type}")
    @ResponseBody
    public  static List queryData(@PathVariable("type")String type){
        WebApplicationContext webApplicationContext=
                org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext=webApplicationContext.getServletContext();
        List<Dictionary> list=(List<Dictionary>) servletContext.getAttribute("dicList");
        List<Dictionary> resList=new ArrayList();
        for(Dictionary dictionary:list){
            if(dictionary.getDictType().equals(type)){
                resList.add(dictionary);
            }
        }
        return resList;
    }
}
