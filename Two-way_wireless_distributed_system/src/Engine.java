import Interface.JobFactory;
import Nodes.Node;


public class Engine {

	
	public void process(Message msg){
		
		 Node node = NodeManager.getNodeManager().getNode(msg.id);
		
		if(msg.type == MessageType.pullInterface){
			//create Interface
			try {
				msg.data = JobFactory.getJob("Mean");
				msg.type = MessageType.pushInterface;
				msg.id = 0;

				node.setStatus(NodeStatus.run);
				node.setJobName("Mean");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
		}else if(msg.type == MessageType.pushResult){
			System.out.println("From Node Id "+msg.id+", Result :"+(String)msg.data);
			msg.type = MessageType.acceptResult;
			msg.id = 0;
		}else{
			System.out.println("Message Not Supported.");
		}
	}
	
}
