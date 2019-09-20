package com.neu.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@RequestMapping("/login")
	public void login(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		System.out.println("login");
		//不放入html会一直循环
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public void loginCheck(HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		// TODO 登录成功后 重定向至页面
		System.out.println("check");
		response.sendRedirect("/His");
	}

	@RequestMapping("/logout")
	public String logout() {
		
		// if 登录了 注销
		
		System.out.println("logout");
//		return "redirect:login";
		return "login.jsp";
	}

	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public void register(HttpServletRequest request , HttpServletResponse response) {
		//TODO 验证数据
		System.out.println("-------------/register-------------");
		
	}
	
}
