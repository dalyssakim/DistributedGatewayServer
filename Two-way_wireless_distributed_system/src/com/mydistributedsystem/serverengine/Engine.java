package com.mydistributedsystem.serverengine;

import com.mydistributedsystem.message.JDMessage;
import com.mydistributedsystem.message.JDMessageType;
import com.mydistributedsystem.nodes.NodeManager;



public class Engine {

	private EngineProcess engine;
	private JDMessage msg;

	public Engine(JDMessage msg){
		this.msg = msg;
		try {
			if(NodeManager.getNodeManager().getNode(msg.id) != null){
			engine = ProcessFactory.EngineProcess(msg);
			}else{
				msg.type = JDMessageType.halt;
			engine = ProcessFactory.EngineProcess(msg);
			}
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
