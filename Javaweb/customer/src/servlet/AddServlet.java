package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.Customer;
import database.DB;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name="/AddServlet",
			urlPatterns= {"/add"})
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//设置编码
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String job=request.getParameter("job");
		String degree=request.getParameter("degree");
		String address=request.getParameter("address");
		DB db = new DB();
		db.init();
		db.add(new Customer(id,name,sex,job,degree,address));
		ArrayList<?> list=db.getResultAll();
		request.setAttribute("list", list);	
		request.getRequestDispatcher("/jsp/CustomerList.jsp").forward(request, response);
		db.close();
	}

}
