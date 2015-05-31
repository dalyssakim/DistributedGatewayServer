package com.mydistributedsystem.serverengine;
import com.mydistributedsystem.message.JDMessage;
import com.mydistributedsystem.nodes.Node;


public abstract class EngineProcess {
	
	public abstract JDMessage process(JDMessage msg);
}
