package com.elin4it.ssm.util;

import com.elin4it.ssm.pojo.SysUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;


public class UserFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(UserFilter.class.toString());
    protected FilterConfig filterConfig;

    public void destroy() {
        filterConfig = null;
    }

    public void loginJsonPath(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) {
        String authError = "loginError";
        arg1.setContentType("text/javascript; charset=utf-8");
        try {
            PrintWriter out = arg1.getWriter();
            out.print(authError);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginPath(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) {
        String loginPath = "/cms/login.html";
        RequestDispatcher loginDispatcher = arg0.getRequestDispatcher(loginPath);
        try {
            loginDispatcher.forward(arg0, arg1);
        } catch (ServletException e) {
            LOGGER.warning(e.toString());
        } catch (IOException e) {
            LOGGER.warning(e.toString());
        }
    }

    public void commonPath(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) {
        try {
            arg2.doFilter(arg0, arg1);
        } catch (IOException e) {
            LOGGER.warning(e.toString());
        } catch (ServletException e) {
            LOGGER.warning(e.toString());
        }
        return;
    }

    /**
     * 判断URL是否需要被过滤
     * 2016-8-2下午7:28:06
     * zhuangjf
     */
    public boolean isAuth(String realUri) {
       boolean isAuth=realUri.matches("(/cms|cms)/.*|(/rest|rest)/.*");
       boolean noAuth=!realUri.matches("(/cms|cms)/(js|dist|plugins|bootstrap)/.*|(/rest|rest)/comweb/.*|(/rest|rest)/user/login");
       return isAuth&&noAuth;
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) {
        HttpServletRequest req = (HttpServletRequest) arg0;
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        String realUri = uri.replace(req.getContextPath().toString(),"");
        String accept = req.getHeader("Accept");

        if (isAuth(realUri)) {
            SysUser sysUser = null;
            sysUser = (SysUser) session.getAttribute("user");
            if (sysUser == null) {
                if (accept.contains("application/json")) {
                    loginJsonPath(arg0, arg1, arg2);
                } else {
                    loginPath(arg0, arg1, arg2);
                }
            } else {
                commonPath(arg0, arg1, arg2);
            }
        } else {
            commonPath(arg0, arg1, arg2);
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        filterConfig = fConfig;
    }
}
