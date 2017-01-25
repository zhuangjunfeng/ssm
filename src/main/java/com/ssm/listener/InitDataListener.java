package com.ssm.listener;

import com.ssm.pojo.Dictionary;
import com.ssm.service.DictionaryService;
import com.ssm.service.DictionaryService;
import org.apache.log4j.Logger;
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
    private static Logger logger = Logger.getLogger(InitDataListener.class);

    @Resource
    private DictionaryService dictionaryService;
    public void  afterPropertiesSet() throws Exception{}
    public void setServletContext(ServletContext servletContext){
        List<Dictionary> list=dictionaryService.findDictionary();
        servletContext.setAttribute("dicList",list);
        logger.info("-------------初始化数据成功----------");
    }
}
