package com.elin4it.ssm.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ServiceStartUpListener implements ServletContextListener{
    public void contextDestroyed(ServletContextEvent arg0) {


    }

    public void contextInitialized(ServletContextEvent arg0) {

        System.out.println("-------------------系统初始化字典数据開始--------------");
        DictionaryUtil.initData(arg0);
        System.out.println("-------------------系统初始化字典数据結束--------------");


    }
}
