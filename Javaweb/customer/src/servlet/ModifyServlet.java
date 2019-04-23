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
 * Servlet implementation class ModifyServlet
 */
@WebServlet(name="/ModifyServlet",urlPatterns= {"/modify"})
public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//设置编码
		int id=Integer.parseInt(request.getParameter("id1"));
		String name=request.getParameter("name1");
		String sex=request.getParameter("sex1");
		String job=request.getParameter("job1");
		String degree=request.getParameter("degree1");
		String address=request.getParameter("address1");
		DB db=new DB();
		db.init();
		db.modify(new Customer(id,name,sex,job,degree,address));
		ArrayList<?> list=db.getResultAll();
		request.setAttribute("list", list);	
		request.getRequestDispatcher("/jsp/CustomerList.jsp").forward(request, response);
		db.close();
		
	}

}
