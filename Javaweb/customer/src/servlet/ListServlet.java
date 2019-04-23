package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DB;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet(name="/ListServlet",urlPatterns= {"/list"})
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));	//删除
		DB db = new DB();
		db.init();
		db.delete(id);
		ArrayList<?> list=db.getResultAll();
		request.setAttribute("list", list);	
		request.getRequestDispatcher("/jsp/CustomerList.jsp").forward(request, response);
		db.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");//设置编码
		String way=request.getParameter("way");		//查询
		String word=request.getParameter("word");
		ArrayList<?> list=null;
		DB db = new DB();	//TODO 未解决多人操作
		db.init();
		if(word==null||word=="")	//为空时加载所有结果
		{
			list=db.getResultAll();
			request.setAttribute("list", list);	
			request.getRequestDispatcher("/jsp/CustomerList.jsp").forward(request, response);		
		}else 
		{	
			if(way.equals("way_id"))	//id精确搜索
			{
				list=db.getResult(Integer.parseInt(word));
				request.setAttribute("list", list);	
				request.getRequestDispatcher("/jsp/CustomerList.jsp").forward(request, response);
			}else if(way.equals("way_name"))	//姓名模糊搜索
			{
				list=db.getResult(word);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/jsp/CustomerList.jsp").forward(request, response);
			}
		}
		db.close();		//关闭资源
	}

}
