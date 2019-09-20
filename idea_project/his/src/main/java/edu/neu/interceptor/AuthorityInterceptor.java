package edu.neu.interceptor;

import edu.neu.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:AuthorityInterceptor
 * Package:edu.neu.interceptor
 * Description: 根据用户权限拦截
 *
 * @Date:2019/9/15 13:32
 * @Author:HetFrame
 */
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        //root权限直接放行
        if (user.getUser_category() != 0) {
            String path = httpServletRequest.getServletPath();
            if (user.getUser_category() == 1) {
                //只有当请求包含register或者registrar或者相关页面时放行
                if (path.indexOf("register") != -1 || path.indexOf("registrar") != -1) {
                    return true;
                }
                //因为是ajax请求所以没有权限时需要设置一个status，前端直接根据它跳转
                if (httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equals("XMLHttpRequest")) {
                    httpServletResponse.setHeader("sessionstatus", "noauthority");
                    return false;
                }
            }
            //门诊医生
            if (user.getUser_category() == 2) {
                //只有当请求包含doctor时放行
                if (path.indexOf("doctor") != -1) {
                    return true;
                }
                //因为是ajax请求所以没有权限时需要设置一个status，前端直接根据它跳转
                if (httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equals("XMLHttpRequest")) {
                    httpServletResponse.setHeader("sessionstatus", "noauthority");
                    return false;
                }
            }
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
