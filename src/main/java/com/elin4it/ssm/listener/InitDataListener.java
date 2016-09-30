package com.elin4it.ssm.listener;

import com.elin4it.ssm.pojo.Dictionary;
import com.elin4it.ssm.service.DictionaryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.List;

/**
 * 内容管理平台服务启动监听器
 * 查询字典数据存入缓存
 * Created by Administrator on 2016/9/2.
 */
@Controller
public class InitDataListener  implements InitializingBean,ServletContextAware{
    @Resource
    private DictionaryService dictionaryService;
    public void  afterPropertiesSet() throws Exception{}
    public void setServletContext(ServletContext servletContext){
        System.out.println("-------------------系统初始化字典数据開始--------------");
        List<Dictionary> list=dictionaryService.findDictionary();
        servletContext.setAttribute("dicList",list);
        System.out.println("-------------------系统初始化字典数据結束--------------");
    }
}
