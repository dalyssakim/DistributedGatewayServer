package EngineProcesses;
import Interface.JobFactory;
import Nodes.Node;
import Nodes.NodeManager;
import Nodes.NodeStatus;
import Message.JDMessage;
import Message.JDMessageType;

public class processHalt extends EngineProcess{

	public  JDMessage process(JDMessage msg){

		try {
			msg.setMessage(0, JDMessageType.halt, JobFactory.getJob("Halt"));
			NodeManager.getNodeManager().changeNodeStatus(msg.id, NodeStatus.ready, "Halt");			
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
}
