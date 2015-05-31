package com.mydistributedsystem.serverengine;

import com.mydistributedsystem.message.JDMessage;



public class Engine {

	private EngineProcess engine;
	private JDMessage msg;
	public Engine(JDMessage msg){
		this.msg = msg;
		try {
			engine = ProcessFactory.EngineProcess(msg.type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public JDMessage process(){
		JDMessage nmsg = engine.process(msg);
		return nmsg;
	}
	
}
