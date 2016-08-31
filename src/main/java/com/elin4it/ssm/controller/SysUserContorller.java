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

    /**
     * @deprecation:登录验证
     * @param yhzh 用户账户
     * @param password 用户密码
     * @param session 存储用户
     * @return 用户信息
     */
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

    /**
     * @deprecation：通过用户Id查询用户
     * @param yhId 用户Id
     * @return用户信息
     * @throws Exception
     */
    @RequestMapping(value = "/findUser/{yhId}",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findUserById( @PathVariable("yhId") int yhId)throws Exception{
         SysUser users = userService.findUserById(yhId);
        return new JSONResult(users);
    }

    /**
     * @deprecation:查询全部用户
     * @return 用户表单
     * @throws Exception
     */
    @RequestMapping(value = "/findAllUser",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findALLUser()throws Exception{
        List<SysUser> users = userService.findAll();
        return new JSONResult(users);
    }

    /**
     * @deprecation:通过用户Id删除用户
     * @param yhId 用户Id
     * @return 是否成功信息
     */
    @RequestMapping(value = "/delUser/{yhId}")
    @ResponseBody
    public JSONResult delUserById(@PathVariable("yhId")int yhId){
        JSONResult result=new JSONResult();
        if(!userService.delUserById(yhId))
            result.setMessage("error");
        return result;
    }

    /**
     * @deprecation：添加用户
     * @param yhzh 用户账户
     * @param password 用户密码
     * @param yhxm 用户姓名
     * @param yhxb 用户性别
     * @param yhyx 用户邮箱
     * @return 返回添加成功与否
     * @throws Exception
     */
    @RequestMapping(value ="/addUser/{yhzh}&{password}&{yhxm}&{yhxb}&{yhyx}")
    @ResponseBody
    public  JSONResult addUser(@PathVariable("yhzh")String yhzh,@PathVariable("password")String password,
                               @PathVariable("yhxm")String yhxm,@PathVariable("yhxb")String yhxb,
                               @PathVariable("yhyx")String yhyx)
        {
            JSONResult result;
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
    @RequestMapping("/updateUser/{yhId}&{yhzh}&{password}&{yhxm}&{yhxb}&{yhyx")
    @ResponseBody
    public JSONResult updateUser(@PathVariable("yhId") int yhId, @PathVariable("yhzh")String yhzh,
                                 @PathVariable("password")String password, @PathVariable("yhxm")String yhxm,
                                 @PathVariable("yhxb")String yhxb, @PathVariable("yhyx")String yhyx){
        JSONResult result=new JSONResult();
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
