package com.elin4it.ssm.util;

import com.elin4it.ssm.pojo.Dictionary;
import com.elin4it.ssm.service.impl.DictionaryServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/1.
 */
public class DictionaryUtil {
    private final static String DICTIONARY_CACHE = "dictionary";
    /**
     * @decription：初始化數據
     * @date 2016-8-3下午8:24:52
     * @author：zhuangjf
     */
    public  static boolean initData(ServletContextEvent arg0){
        final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
        final DictionaryServiceImpl dictionaryService = (DictionaryServiceImpl)springContext.getBean("dictionaryService"); // 关键代码

        ServletContext application = arg0.getServletContext();
        List<Dictionary> dict = dictionaryService.findDictionary();
        application.setAttribute(DICTIONARY_CACHE, dict);
        return true;
    }

    public  static List queryData(String type,Map application){
        List<Dictionary> list=(List<Dictionary>) application.get("dictionary");
        List<Dictionary> resList=new ArrayList();
        for(Dictionary dictionary:list){
            if(dictionary.getDictType().equals(type)){
                resList.add(dictionary);
            }
        }
        return resList;
    }
}
