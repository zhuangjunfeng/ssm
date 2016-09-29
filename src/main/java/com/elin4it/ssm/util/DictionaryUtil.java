package com.elin4it.ssm.util;

import com.elin4it.ssm.pojo.Dictionary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
@Controller
@RequestMapping("/**/dict")
public class DictionaryUtil {
    private final static String DICTIONARY_CACHE = "dictionary";
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public  static JSONResult queryData(HttpServletRequest  request){
        WebApplicationContext webApplicationContext=
                org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext=webApplicationContext.getServletContext();
        String type=request.getParameter("type");
        List<Dictionary> list=(List<Dictionary>) servletContext.getAttribute("dicList");
        List<Dictionary> resList=new ArrayList();
        for(Dictionary dictionary:list){
            if(dictionary.getDictType().equals(type)){
                resList.add(dictionary);
            }
        }
        return new JSONResult(resList);
    }

    @RequestMapping(value = "/findDictProgram",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findDictProgram(HttpServletRequest  request){
        WebApplicationContext webApplicationContext=
                org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext=webApplicationContext.getServletContext();
        String type=request.getParameter("type");
        List<Dictionary> list=(List<Dictionary>) servletContext.getAttribute("dicList");
        List<Dictionary> resList=new ArrayList();
        for(Dictionary dictionary:list){
            if(type.equals(dictionary.getDictFather())){
                resList.add(dictionary);
            }
        }
        return new JSONResult(resList);
    }
    @RequestMapping(value = "/findDict",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findDict(HttpServletRequest  request){
        WebApplicationContext webApplicationContext=
                org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext=webApplicationContext.getServletContext();
        String type=request.getParameter("type");
        List<Dictionary> list=(List<Dictionary>) servletContext.getAttribute("dicList");
        List<Dictionary> resList=new ArrayList();
        for(Dictionary dictionary:list){
            if(type.indexOf(dictionary.getDictFather())!=-1){
                resList.add(dictionary);
            }
        }
        return new JSONResult(resList);
    }
    @RequestMapping(value = "/findDictType",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findDictType(HttpServletRequest  request) throws UnsupportedEncodingException {
        WebApplicationContext webApplicationContext=
                org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext=webApplicationContext.getServletContext();
        String type=request.getParameter("type");
        type= URLDecoder.decode(type, "UTF-8");
        List<Dictionary> list=(List<Dictionary>) servletContext.getAttribute("dicList");
        List<Dictionary> resList=new ArrayList();
        for(Dictionary dictionary:list){
            if(type.equals(dictionary.getDictName())){
                String dictType=dictionary.getDictType();
                for(Dictionary dictionarys:list){
                if(dictType.equals(dictionarys.getDictFather()))
                    resList.add(dictionarys);
                }
            }
        }
        return new JSONResult(resList);
    }
}
