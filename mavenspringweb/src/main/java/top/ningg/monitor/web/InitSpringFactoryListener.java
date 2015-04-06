package top.ningg.monitor.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitSpringFactoryListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
		arg0.getServletContext().setAttribute("SpringApplicationContext", applicationContext);
		
	}

}
