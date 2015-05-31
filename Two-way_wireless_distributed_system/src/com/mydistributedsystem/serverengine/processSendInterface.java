package com.mydistributedsystem.serverengine;

import com.mydistributedsystem.interfaces.JobFactory;
import com.mydistributedsystem.message.JDMessage;
import com.mydistributedsystem.message.JDMessageType;
import com.mydistributedsystem.nodes.NodeManager;
import com.mydistributedsystem.nodes.NodeStatus;



public class processSendInterface extends EngineProcess{

	public JDMessage process(JDMessage msg) {
		// TODO Auto-generated method stub
		try {
			NodeManager.getNodeManager().changeNodeStatus(msg.id, NodeStatus.run, "Mean");			
			msg.setMessage(0, JDMessageType.pushInterface, JobFactory.getJob("Mean"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

}
