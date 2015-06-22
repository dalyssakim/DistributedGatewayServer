package com.mydistributedsystem.modules;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.mydistributedsystem.faulttolerance.Timer;
import com.mydistributedsystem.interfaces.JobFactory;
import com.mydistributedsystem.nodes.GatewayNode;
import com.mydistributedsystem.nodes.Group;
import com.mydistributedsystem.nodes.Node;
import com.mydistributedsystem.nodes.NodeManager;
import com.mydistributedsystem.nodes.NodeStatus;
import com.mydistributedsystem.ui.SystemMonitor;


public class ServerMain {
	public static Group group1 = new Group();
	public static ArrayList<Group> glist = new ArrayList();
	
	static int dpacketsize = 1024;
	public static void main(String[] args) throws InterruptedException, UnknownHostException{
		
		JobFactory.setInterfaceSource("com.mydistributedsystem.interfaces");
		Node node = new GatewayNode();
		Node node2 = new GatewayNode();

		node.setId(2);
		node.setGid(1);
		node.setPriority(1);
		node.setJobName("JobMean");
		node.setStatus(NodeStatus.disconnected);

		node2.setId(3);
		node2.setPriority(2);
		node2.setGid(1);
		node2.setJobName("JobMean");
		node2.setStatus(NodeStatus.disconnected);

		
		group1.gid = 1;
		group1.nodeList.add(node);
		group1.nodeList.add(node2);
		glist.add(group1);
		NodeManager.getNodeManager().addNode(node.getId(), node );
		NodeManager.getNodeManager().addNode(node2.getId(), node2 );
		Timer timer = new Timer();
		timer.start();

		NodeManager.getNodeManager().addObserver(SystemMonitor.getMonitor());
		SystemMonitor.getMonitor().prepareGUI();
			// Getting the address of clients
		JDModule module = new JDModule(dpacketsize, "DAJUNG-SERVER");
		module.startServer();
		
	}
}
