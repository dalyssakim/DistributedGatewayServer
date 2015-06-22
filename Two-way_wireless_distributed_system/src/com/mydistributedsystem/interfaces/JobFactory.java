package com.mydistributedsystem.interfaces;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

import com.mydistributedsystem.socket.AbstractSocket;

public class JobFactory {

	private static String interfaceSource = "com.mydistributedsystem.interfaces";
	
	public static void setInterfaceSource(String source){
		interfaceSource = source;
		interfaceSource += ".";
	}
	public static Job getJob(String type) throws ClassNotFoundException, InstantiationException, IllegalAccessException{

		Job job = null;
		
		if(type.endsWith("class")){
			DynamicImpLoader loader = new DynamicImpLoader();
		try {
			
			DynamicImpLoader.addFile("C:\\Users\\Dajung\\Desktop\\accumulate.jar");
	        Constructor<?> cs = ClassLoader.getSystemClassLoader().loadClass("JobAccumulate").getConstructor();
	       byte [] data = serialize( cs.newInstance() );
	        job = (Job) deserialize(data);
			
			if(job == null){
				System.out.println("Not an implementation of Job");
				Class C = Class.forName(interfaceSource+"JobHalt"); 
				job = (Job) C.newInstance();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Not an implementation of Job");
			Class C = Class.forName(interfaceSource+"JobHalt"); 
			job = (Job) C.newInstance();
		}
			
		}else{
		Class C = Class.forName(interfaceSource+type); 
		job = (Job) C.newInstance();
		}
		return job;
	}
	
	public static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	
	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}
}
