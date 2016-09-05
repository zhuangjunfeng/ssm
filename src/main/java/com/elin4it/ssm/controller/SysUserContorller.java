package com.elin4it.ssm.controller;

import com.elin4it.ssm.pojo.SysUser;
import com.elin4it.ssm.service.SysUserService;
import com.elin4it.ssm.util.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class SysUserContorller {
    @Resource
    private SysUserService userService;

    /**
     * @deprecation:登录验证
     * @param session 存储用户
     * @return 用户信息
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult login(HttpServletRequest request, HttpSession session){
        JSONResult result;
        String yhzh=request.getParameter("yhzh");
        String password=request.getParameter("password");
        if(yhzh==null||password==null){
            result=new JSONResult();
            result.setMessage("error_null");
            return result;
        }
        SysUser users=userService.findUserByYhzh(yhzh);
        if(users.equals(null)||!users.getPassword().equals(password)){
            result=new JSONResult();
            result.setMessage("error_error");
            return result;
        }
        result=new JSONResult(users);
        result.setMessage("success");
        session.setAttribute("user",users);
        return result;
    }

    /**
     * @deprecation：通过用户Id查询用户
     * @return用户信息
     * @throws Exception
     */
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findUserById(HttpServletRequest request) throws Exception{
         int yhId=Integer.parseInt(request.getParameter("yhId"));
         SysUser users = userService.findUserById(yhId);
        return new JSONResult(users);
    }

    /**
     * @deprecation:查询全部用户
     * @return 用户表单
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findALLUser()throws Exception{
        List<SysUser> users = userService.findAll();
        return new JSONResult(users);
    }

    /**
     * @deprecation:通过用户Id删除用户
     * @return 是否成功信息
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public JSONResult delUserById(HttpServletRequest request){
        JSONResult result=new JSONResult();
        int yhId=Integer.parseInt(request.getParameter("yhId"));
        System.out.println(yhId);
        if(!userService.delUserById(yhId))
            result.setMessage("error");
        return result;
    }
    /**
     * @deprecation：添加用户
     * @return 返回添加成功与否
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public  JSONResult addUser(HttpServletRequest request)
        {
            JSONResult result;
            String yhzh=request.getParameter("yhzh");
            String password=request.getParameter("password");
            String yhxm=request.getParameter("yhxm");
            String yhxb=request.getParameter("yhxb");
            String yhyx=request.getParameter("yhyx");
            SysUser user=new SysUser();
            user.setYhzh(yhzh);
            user.setPassword(password);
            user.setYhxm(yhxm);
            user.setYhxb(yhxb);
            user.setYhyx(yhyx);
          if (userService.addUser(user))
            {
                result=new JSONResult(user);
                result.setMessage("success");
            }
            else{
              result=new JSONResult();
              result.setMessage("error");
            }
            return result;
        }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public JSONResult updateUser(HttpServletRequest request){
        JSONResult result=new JSONResult();
        int yhId=Integer.parseInt(request.getParameter("yhId"));
        String yhzh=request.getParameter("yhzh");
        String password=request.getParameter("password");
        String yhxm=request.getParameter("yhxm");
        String yhxb=request.getParameter("yhxb");
        String yhyx=request.getParameter("yhyx");
        SysUser user=new SysUser();
        user.setYhId(yhId);
        user.setYhzh(yhzh);
        user.setPassword(password);
        user.setYhxm(yhxm);
        user.setYhxb(yhxb);
        user.setYhyx(yhyx);
        if (userService.updateUser(user)){
            result.setMessage("success");
        }
        else{
            result.setMessage("error");
        }
        return  result;
    }
}
