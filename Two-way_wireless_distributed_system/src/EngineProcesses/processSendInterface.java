package EngineProcesses;
import Interface.JobFactory;
import Nodes.Node;
import Nodes.NodeManager;
import Nodes.NodeStatus;
import Message.JDMessage;
import Message.JDMessageType;


public class processSendInterface extends EngineProcess{

	@Override
	public JDMessage process(JDMessage msg) {
		// TODO Auto-generated method stub
		try {
			NodeManager.getNodeManager().changeNodeStatus(msg.id, NodeStatus.run, "Mean");			
			msg.setMessage(0, JDMessageType.pushInterface, JobFactory.getJob("Mean"));
			
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

}
