package com.mydistributedsystem.serverengine;
import com.mydistributedsystem.interfaces.Job;
import com.mydistributedsystem.message.JDMessage;
import com.mydistributedsystem.message.JDMessageType;
import com.mydistributedsystem.modules.ServerMain;
import com.mydistributedsystem.nodes.NodeManager;
import com.mydistributedsystem.nodes.NodeStatus;


public class ProcessFactory {


	
	public static EngineProcess EngineProcess(JDMessage msg) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		if(NodeManager.getNodeManager().getNode(msg.id).isJobChanged()){
			NodeManager.getNodeManager().getNode(msg.id).setJobChanged(false);
			return new processSendInterface();
		}
		
		for(int i = 0; i < ServerMain.group1.group.size(); i++){
			if(ServerMain.group1.group.get(i).getStatus() == NodeStatus.run && ServerMain.group1.group.get(i).getId() != msg.id){
				 return new processHalt();
			}
		}
		
		switch (msg.type){
		case   JDMessageType.pullInterface : return new processSendInterface();
		case   JDMessageType.halt : return new processHalt();
		case   JDMessageType.pushResult : return new processAcceptResult();
		
		}
		
		return null;
	}

}
