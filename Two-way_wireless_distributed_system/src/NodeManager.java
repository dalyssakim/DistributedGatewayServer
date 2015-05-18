
import java.util.HashMap;
import Nodes.Node;


public class NodeManager {

	private static NodeManager nodeManager;
	private HashMap nodeMap = new HashMap();

	
	public static NodeManager getNodeManager(){
		
		if(nodeManager == null) 
			{
				nodeManager = new NodeManager(); 
			}
		return nodeManager;
	}
	
	public void addNode(int id, Node node){
		nodeMap.put(Integer.toString(id), node);
	}
	
	public Node getNode(int id){
		return (Node) nodeMap.get(Integer.toString(id));
	}
}
