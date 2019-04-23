package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 	过滤jsp文件夹内内容以及三个servlet
 */

@WebFilter(filterName="MyFilter",urlPatterns= {"/jsp/*"},servletNames= {"ListServlet","UploadServlet","LogoutServlet"})//设置拦截所有jsp结尾的网页以及需过滤的servlet(需在web.xml注册servlet)
public class MyFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);//获取session
		if(session!=null) {//session不为空后且用户名也不为空说明是已登录状态
			String id = (String) session.getAttribute("id");
			if("".equals(id)||null==id)//id为空跳转至登录页面
			{
				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendRedirect("/goods/login.jsp");
			} else {//id不为空处于登录状态 放行
				chain.doFilter(request, response);
				return;
			}
		}else {//session为空直接跳转至登录页面
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect("/goods/login.jsp");	
		}
	}


}
