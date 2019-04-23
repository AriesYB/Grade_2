package servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBUtil;

/**
 * 	通过id读取数据库并转发至商品列表
 */
public class ListServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object attribute = request.getAttribute("list");
		if(attribute==null)
		{
			//查询数据
			String sql = "SELECT * FROM good_info";
			DBUtil.getDBUtil().getConn();
			ArrayList<?> list = DBUtil.getDBUtil().executeQuery(sql);
			DBUtil.getDBUtil().close();
			request.setAttribute("list", list);
		}
		request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
	}

}
