package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;//全用commons的包
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import util.DBUtil;

/**
 * 	上传文件
 */

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String good_id="";//这是获取的商品id
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	  
		//判断是否为multipart请求
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new RuntimeException("当前请求不支持文件上传");
		}
		try {
			
			//创建一个FileItem工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 创建文件上传核心组件
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			//解析请求
			List<FileItem> items = upload.parseRequest(request);
			// 遍历items
			for (FileItem item : items) {
				if (item.isFormField()) { // 若item为普通表单
						String name = item.getFieldName(); // 获取表单项名称
						if ("good_id".equals(name)) {
						good_id = item.getString("UTF-8"); // 获取表单项值并解决乱码
					}
				} else { // 若item为文件表单项
					String fileName = item.getName();
					if("".equals(fileName)||null==fileName)
					{
						continue;
					}
					InputStream is = item.getInputStream();
					//获取文件保存在服务器的路径
					/*String path = request.getServletContext().getRealPath("/WEB-INF/upload"); 不便访问 不存入这个文件夹*/
					String path = request.getServletContext().getRealPath("/jsp/image");
					//创建目标文件，用来保存上传文件
					File descFile = new File(path, fileName);
					//创建文件输出流
					FileOutputStream out = new FileOutputStream(descFile);
					//将输入流中的数据写入到输出流中
					int len = 0;
					byte[] buffer = new byte[1024];
					while((len = is.read(buffer))>0) {
						out.write(buffer, 0, len);
					}
					//关闭流
					out.close();
					is.close();
					//删除临时文件
					item.delete();
					//操作数据库 
					String sql="UPDATE good_info SET imgUrl='"+fileName+"'"+"WHERE id="+good_id;
					DBUtil.getDBUtil().getConn();
					DBUtil.getDBUtil().executeUpdate(sql);
					DBUtil.getDBUtil().close();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
					response.sendRedirect("list");
					return;
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
