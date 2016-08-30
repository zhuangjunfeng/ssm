package com.elin4it.ssm.controller;

import com.elin4it.ssm.pojo.SysUser;
import com.elin4it.ssm.service.SysUserService;
import com.elin4it.ssm.util.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class SysUserContorller {
    @Resource
    private SysUserService userService;

    @RequestMapping(value = "/login/{yhzh}&&{password}")
    @ResponseBody
    public JSONResult login(@PathVariable("yhzh")String yhzh,@PathVariable("password")String password, HttpSession session){
        JSONResult result;
        if(yhzh==null||password==null){
            result=new JSONResult();
            result.setMessage("error_null");
            return result;
        }
        SysUser users=userService.findUserByYhzh(yhzh);
        if(users.equals(null)||!users.getPassword().equals(password)){
            result=new JSONResult(users);
            result.setMessage("error_error");
            return result;
        }
        result=new JSONResult(users);
        result.setMessage("success");
        session.setAttribute("user",users);
        return result;
    }
    @RequestMapping(value = "/findUser/{yhId}",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findUserById( @PathVariable("yhId") int yhId)throws Exception{
         SysUser users = userService.findUserById(yhId);
        return new JSONResult(users);
    }
//
    @RequestMapping(value = "/findAllUser",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findALLUser()throws Exception{
        List<SysUser> users = userService.findAll();
        return new JSONResult(users);
    }
}
