package EngineProcesses;
import Interface.JobFactory;
import Message.JDMessage;
import Message.JDMessageType;


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
