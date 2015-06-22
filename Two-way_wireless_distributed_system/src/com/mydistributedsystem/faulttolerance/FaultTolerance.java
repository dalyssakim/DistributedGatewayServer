package com.mydistributedsystem.faulttolerance;

import com.mydistributedsystem.modules.ServerMain;
import com.mydistributedsystem.nodes.Group;
import com.mydistributedsystem.nodes.Node;
import com.mydistributedsystem.nodes.NodeStatus;

public class FaultTolerance extends Thread{

	public void election(Group fault_group){
		Node tmp = fault_group.currentNode ;
		tmp.setStatus(NodeStatus.disconnected);
		
		for(Node n : fault_group.nodeList){
			if(n.getStatus() == NodeStatus.ready){
				n.setStatus(NodeStatus.connected);
				n.setRecentTs(Timer.ts);
				fault_group.currentNode = n ;
				break ;
			}
		}
	}
	public void faultDetector(){
		for(Group g : ServerMain.glist){
			if(Timer.ts - g.currentNode.getRecentTs() > 50){
				election(g);
			}
		}
	}
	public void run(){
		try {
			Thread.sleep(100);
			this.faultDetector();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void startFaultTolerace(){
		this.start();
	}
	
}
