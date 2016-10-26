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
/**
 * 内容管理平台用户模块控制层
 * Created by Administrator on 2016/8/31.
 */
@Controller
@RequestMapping("/user")
public class SysUserController {
    @Resource
    private SysUserService userService;

    /**
     * 登录验证
     * @param session 存储用户
     * @return 用户信息
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult login(HttpServletRequest request, HttpSession session) {
        JSONResult result;
        String yhzh = request.getParameter("yhzh");
        String password = request.getParameter("password");
        if (yhzh == null || password == null) {
            result = new JSONResult();
            result.setMessage("error_null");
            return result;
        }
        SysUser users = userService.findUserByYhzh(yhzh);
        if (users.equals(null) || !users.getPassword().equals(password)) {
            result = new JSONResult();
            result.setMessage("error_error");
            return result;
        }
        result = new JSONResult(users);
        result.setMessage("success");
        session.setAttribute("user", users);
        return result;
    }

    /**
     * 登出方法
     * @param session 登录用户信息
     * @return null
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult logout(HttpSession session) {
        session.setAttribute("user", null);
        return new JSONResult();
    }

    /**
     * 通过用户Id查询用户
     * @throws Exception
     * @return 用户信息
     */
    @RequestMapping(value = "/findUser", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findUserById(HttpServletRequest request) throws Exception {
        int yhId = Integer.parseInt(request.getParameter("yhId"));
        SysUser users = userService.findUserById(yhId);
        return new JSONResult(users);
    }

    /**
     * 查询全部用户
     * @return 用户表单
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findALLUser() throws Exception {
        List<SysUser> users = userService.findAll();
        return new JSONResult(users);
    }

    /**
     * 查询登录用户信息
     * @param request null
     * @return 登录用户信息
     */
    @RequestMapping(value = "/findLoginUser", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult findUser(HttpServletRequest request) {
        SysUser users = (SysUser) request.getSession().getAttribute("user");
        SysUser loginUser = new SysUser();
        loginUser.setYhId(users.getYhId());
        loginUser.setYhxm(users.getYhxm());
        loginUser.setYhzh(users.getYhzh());
        loginUser.setYhxb(users.getYhxb());
        loginUser.setYhjs(users.getYhjs());
        return new JSONResult(loginUser);
    }

    /**
     * 通过用户Id删除用户
     * @return 是否成功信息
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public JSONResult delUserById(HttpServletRequest request) {
        JSONResult result = new JSONResult();
        String Yhid = request.getParameter("yhId");
        int yhId = Integer.parseInt(Yhid);
        System.out.println(yhId);
        if (!userService.delUserById(yhId))
            result.setMessage("error");
        return result;
    }

    /**
     * 添加用户
     * @return 返回添加成功与否
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addUser(HttpServletRequest request) {
        JSONResult result;
        String yhzh = request.getParameter("yhzh");
        String password = request.getParameter("password");
        String yhxm = request.getParameter("yhxm");
        String yhxb = request.getParameter("yhxb");
        String yhyx = request.getParameter("yhyx");
        String yhjs =request.getParameter("yhjs");
        SysUser user = new SysUser();
        user.setYhzh(yhzh);
        user.setPassword(password);
        user.setYhxm(yhxm);
        user.setYhxb(yhxb);
        user.setYhyx(yhyx);
        user.setYhjs(yhjs);
        if (userService.addUser(user)) {
            result = new JSONResult(user);
            result.setMessage("success");
        } else {
            result = new JSONResult();
            result.setMessage("error");
        }
        return result;
    }

    /**
     * 根据用户ID更新用户
     * @param request 用户ID
     * @return 是否成功信息
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public JSONResult updateUser(HttpServletRequest request) {
        JSONResult result = new JSONResult();
        int yhId = Integer.parseInt(request.getParameter("yhId"));
        String yhzh = request.getParameter("yhzh");
        String password = request.getParameter("password");
        String yhxm = request.getParameter("yhxm");
        String yhxb = request.getParameter("yhxb");
        String yhyx = request.getParameter("yhyx");
        String yhjs =request.getParameter("yhjs");
        SysUser user = new SysUser();
        user.setYhId(yhId);
        user.setYhzh(yhzh);
        user.setPassword(password);
        user.setYhxm(yhxm);
        user.setYhxb(yhxb);
        user.setYhyx(yhyx);
        user.setYhjs(yhjs);
        if (userService.updateUser(user)) {
            result.setMessage("success");
        } else {
            result.setMessage("error");
        }
        return result;
    }
}
