package Interface;

import Socket.AbstractSocket;

public class JobFactory {

	public static Job getJob(String type) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class C = Class.forName("Interface.Job"+type); 
		Job job = (Job) C.newInstance();
		
		return job;
	}
	
}
