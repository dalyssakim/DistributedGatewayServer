package com.mydistributedsystem.serverengine;
import com.mydistributedsystem.interfaces.JobFactory;
import com.mydistributedsystem.message.JDMessage;
import com.mydistributedsystem.message.JDMessageType;


public class processAcceptResult extends EngineProcess{


	public  JDMessage process(JDMessage msg){

		/*
		 * Do whatever needed to save the result
		 */
		System.out.println("From Gateway Node["+msg.id+"]- Result : "+msg.data);
			msg.setMessage(0, JDMessageType.acceptResult, null);
	//		NodeManager.getNodeManager().changeNodeStatus(msg.id, NodeStatus.ready, "Halt");			
			
		return msg;
	}
}
