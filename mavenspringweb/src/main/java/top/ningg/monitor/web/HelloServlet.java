package top.ningg.monitor.web;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import top.ningg.monitor.service.IHello;

@WebServlet(name="HelloServlet",urlPatterns={"/hello"})
public class HelloServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IHello hello;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		
		//方法1：传统方式加载beans.xml，每次请求都加载
		//疑问：how to make sure the absolute path
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
		
		hello = applicationContext.getBean(IHello.class);
		String sayHi = hello.sayHi();
		System.err.println("sayHi:" + sayHi);
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("<h4>" + sayHi + "</h4>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
