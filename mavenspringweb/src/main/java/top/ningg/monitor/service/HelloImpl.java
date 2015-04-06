package top.ningg.monitor.service;

import java.util.Date;

public class HelloImpl implements IHello{

	String msg;

	public void setMsg(String msg){
		this.msg = msg;
	}
	
	@Override
	public String sayHi() {
		return "Now the time is: " + new Date() + "; The msg is: " + msg;
	}

}
