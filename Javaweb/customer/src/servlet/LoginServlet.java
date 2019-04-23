package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.Account;
import database.DB;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(name="/LoginServlet",
			urlPatterns= {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//doget方法传输更快	
		if("login".equals(request.getParameter("action")))//登录
		{		
			DB db = new DB();	//TODO 多人操作未处理
			db.init();
			if(db.check(Integer.parseInt(request.getParameter("user")),request.getParameter("password")))
			{
				ArrayList<?> list=db.getResultAll();
				request.setAttribute("list", list);	
				request.getRequestDispatcher("/jsp/CustomerList.jsp").forward(request, response);
			}else
			{
				response.sendRedirect("/customer/jsp/Login.jsp?errorTip="+URLEncoder.encode("密码错误","utf-8"));               
			}
			db.close();
		}
		if("register".equals(request.getParameter("action")))//注册
		{
			int account=Integer.parseInt(request.getParameter("account1"));
			String password=request.getParameter("password1");
			DB db = new DB();
			db.init();
			db.register(new Account(account,password));
			request.getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
			db.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
