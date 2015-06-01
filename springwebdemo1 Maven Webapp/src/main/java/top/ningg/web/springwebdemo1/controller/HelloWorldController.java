package top.ningg.web.springwebdemo1.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> redisTemplate; 
	
	String message = "Welcome to Spring MVC world!";

	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {

		ModelAndView model = new ModelAndView("helloworld");
		model.addObject("message", message);
		model.addObject("name", name);
		
		System.out.println(redisTemplate.opsForValue().get("cib_00"));
		// System.out.println(redisTemplate.opsForHash().get("cib_00", "field"));

		return model;
	}
	
	

}
