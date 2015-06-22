package com.mydistributedsystem.faulttolerance;

import com.mydistributedsystem.modules.ServerMain;
import com.mydistributedsystem.nodes.Group;
import com.mydistributedsystem.nodes.Node;
import com.mydistributedsystem.nodes.NodeManager;
import com.mydistributedsystem.nodes.NodeStatus;

public class FaultTolerance extends Thread{

	public static boolean runFaultTolerance = true;
	public void election(Group fault_group){
		NodeManager.getNodeManager().changeNodeStatus(fault_group.currentNode.getId(), NodeStatus.disconnected, null);
		
		for(Node n : fault_group.nodeList){
			if(n.getStatus() == NodeStatus.ready){
				NodeManager.getNodeManager().changeNodeStatus(n.getId(), NodeStatus.run, null);
				n.setRecentTs(Timer.ts);
				fault_group.currentNode = n ;
				break ;
			}
		}
	}
	public void faultDetector(){
		if(ServerMain.glist != null){
		for(Group g : ServerMain.glist){
			if(g.currentNode != null){
			if(Timer.ts - g.currentNode.getRecentTs() > 10){
				election(g);
			}
		}
		}
		}
	}
	public void run(){
		try {
			while(runFaultTolerance){
			Thread.sleep(5000);
			faultDetector();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void startFaultTolerance(){
		this.start();
	}
	
}
