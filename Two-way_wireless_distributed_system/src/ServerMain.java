import java.net.UnknownHostException;

import Nodes.GatewayNode;
import Nodes.Node;


public class ServerMain {
	static int dpacketsize = 1024;
	public static void main(String[] args) throws InterruptedException, UnknownHostException{
		Node node = new GatewayNode();
		node.setId(2);
		node.setJobName("NOJOB");
		node.setStatus(NodeStatus.disconnected);

		Node node2 = new GatewayNode();
		node.setId(3);
		node.setJobName("NOJOB");
		node.setStatus(NodeStatus.disconnected);

		NodeManager.getNodeManager().addNode(2, node );
		NodeManager.getNodeManager().addNode(3, node2 );
		
			// Getting the address of clients
		JDModule module = new JDModule(dpacketsize, "DAJUNG-SERVER");
		module.startServer();
	}
}
