package com.mydistributedsystem.serverengine;

import com.mydistributedsystem.interfaces.JobFactory;
import com.mydistributedsystem.interfaces.JobMean;
import com.mydistributedsystem.message.JDMessage;
import com.mydistributedsystem.message.JDMessageType;
import com.mydistributedsystem.nodes.NodeManager;
import com.mydistributedsystem.nodes.NodeStatus;



public class processSendInterface extends EngineProcess{

	public JDMessage process(JDMessage msg) {
		// TODO Auto-generated method stub
		try {
	//		NodeManager.getNodeManager().changeNodeStatus(msg.id, NodeStatus.run, "JobMean");			
		//	msg.setMessage(0, JDMessageType.pushInterface, JobFactory.getJob("JobMean"));
			
				String path = 			"C:\\Users\\Dajung\\workspace\\StrategyPattern\\bin\\JobAccumulate.class";
				//	NodeManager.getNodeManager().changeNodeStatus(msg.id, NodeStatus.run, 	NodeManager.getNodeManager().getNode(msg.id).getJobName());			
					msg.setMessage(0, JDMessageType.pushInterface, 	JobFactory.getJob(NodeManager.getNodeManager().getNode(msg.id).getJobName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

}
