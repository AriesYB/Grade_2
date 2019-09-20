package handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	
	@RequestMapping("/some.do")
	public void doSome(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		System.out.println("some");
		request.getRequestDispatcher("/index.html").forward(request, response);
	}
	
}
