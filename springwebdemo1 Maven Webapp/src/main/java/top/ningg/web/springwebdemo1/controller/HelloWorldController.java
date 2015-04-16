package top.ningg.web.springwebdemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	String message = "Welcome to Spring MVC world!";
	
	public ModelAndView showMessage(String name){
		
		ModelAndView model = new ModelAndView("helloworld");
//		model.add
		
		return model;
	}
	
}
