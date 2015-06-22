package com.mydistributedsystem.nodes;

import java.util.HashMap;
import java.util.Observable;

import com.mydistributedsystem.nodes.Node;


public class NodeManager extends Observable{

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

		nodeMap.put(id+"", node);
		updateUI(getNode(id));
	}
	
	public Node getNode(int id){

		return (Node) nodeMap.get(id+"");
	}
	
	public void changeNodeStatus(int id, int type, String jobName){
		
		
		
		Node node = getNode(id);
		if(node != null){
			if(type != -1){
		node.setStatus(type);
			}
		if(jobName != null){
		node.setJobName(jobName);
		}
		updateUI(node);

		}
	}
	
	protected void updateUI(Node node){
		setChanged();
		this.notifyObservers(node);
	}
}
