package util;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 	session属性监听器，登录后会添加id到session，记录人数增加，注销会移除id，人数减少
 */

@WebListener
public class MyListener implements HttpSessionAttributeListener {

	private int number;
	public void attributeAdded(HttpSessionBindingEvent se) {
		number++;
		ServletContext context = se.getSession().getServletContext();
		context.setAttribute("userNum", number);
		System.out.println("在线人数+1："+number+"人");
	}


	public void attributeRemoved(HttpSessionBindingEvent se) {
		number--;
		ServletContext context = se.getSession().getServletContext();
		context.setAttribute("userNum", number);
		System.out.println("在线人数-1："+number+"人");
	}

}
