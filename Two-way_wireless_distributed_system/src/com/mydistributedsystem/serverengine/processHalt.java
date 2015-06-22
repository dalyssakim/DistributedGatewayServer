package com.mydistributedsystem.serverengine;

import com.mydistributedsystem.interfaces.JobFactory;
import com.mydistributedsystem.message.JDMessage;
import com.mydistributedsystem.message.JDMessageType;
import com.mydistributedsystem.nodes.NodeManager;
import com.mydistributedsystem.nodes.NodeStatus;


public class processHalt extends EngineProcess{

	public  JDMessage process(JDMessage msg){

		try {
			NodeManager.getNodeManager().changeNodeStatus(msg.id, NodeStatus.ready, NodeManager.getNodeManager().getNode(msg.id).getJobName());
			
			msg.setMessage(0, JDMessageType.halt, JobFactory.getJob("JobHalt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
}
