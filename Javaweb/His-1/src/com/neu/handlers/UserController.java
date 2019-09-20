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
		// FIXME 未知bug 突然访问不了
		System.out.println("-------------/login-------------");
		request.getRequestDispatcher("html/login.html").forward(request, response);
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public void loginCheck(HttpServletRequest request , HttpServletResponse response) throws IOException {
		
		System.out.println("-------------/验证成功登录-------------");
		// TODO 登录成功后 重定向至页面
		response.sendRedirect("/His");
	}

	@RequestMapping("/logout")
	public String logout() {
		// if 登录了 注销
		
		System.out.println("-------------/logout-------------");
		return "redirect:/His";//???
	}

	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public void register() {
		//TODO 验证数据
		System.out.println("-------------/register-------------");
		
		
	}
	
}
