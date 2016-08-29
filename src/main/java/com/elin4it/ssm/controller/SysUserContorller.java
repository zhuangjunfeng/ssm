package com.elin4it.ssm.controller;

import com.elin4it.ssm.pojo.SysUser;
import com.elin4it.ssm.service.SysUserService;
import com.elin4it.ssm.util.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class SysUserContorller {
    @Resource
    private SysUserService userService;

    @RequestMapping(value = "/findUser")
    public ModelAndView findUser()throws Exception{
        ModelAndView modelAndView = new ModelAndView();
         SysUser users = userService.findUser(1);
        modelAndView.addObject("users",users);
        modelAndView.setViewName("findUser");
        return modelAndView;
    }
//
    @RequestMapping("/findAll")
    @ResponseBody
    public JSONResult findALL()throws Exception{
//        ModelAndView modelAndView = new ModelAndView();
        List<SysUser> users = userService.findAll();
//        modelAndView.addObject("users",users);
//        modelAndView.setViewName("findAll");
//        return modelAndView;
        return new JSONResult(users);
    }
}
