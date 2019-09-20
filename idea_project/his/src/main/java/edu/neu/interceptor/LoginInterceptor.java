package edu.neu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName:PermissionInterceptor
 * Package:edu.neu.interceptor
 * Description:
 *
 * @Date:2019/8/31 15:36
 * @Author:HetFrame
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            //如果是ajax请求那么直接设置一个sessionstatus，前端直接根据它跳转
            if (httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equals("XMLHttpRequest")) {
                httpServletResponse.setHeader("sessionstatus", "timeout");
                return false;
            }
            // 普通请求直接重定向至登录页面
            httpServletResponse.sendRedirect("login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
