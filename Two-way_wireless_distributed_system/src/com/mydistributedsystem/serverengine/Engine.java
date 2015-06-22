package com.mydistributedsystem.serverengine;

import com.mydistributedsystem.faulttolerance.Timer;
import com.mydistributedsystem.message.JDMessage;
import com.mydistributedsystem.message.JDMessageType;
import com.mydistributedsystem.nodes.NodeManager;
import com.mydistributedsystem.nodes.NodeStatus;



public class Engine {

	private EngineProcess engine;
	private JDMessage msg;

	public Engine(JDMessage msg){
		this.msg = msg;
		try {
			if(NodeManager.getNodeManager().getNode(msg.id) != null){
			engine = ProcessFactory.EngineProcess(msg);

			NodeManager.getNodeManager().getNode(msg.id).setRecentTs(Timer.ts);
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
