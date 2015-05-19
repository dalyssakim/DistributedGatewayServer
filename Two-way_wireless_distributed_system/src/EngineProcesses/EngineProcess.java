package EngineProcesses;
import Message.JDMessage;
import Nodes.Node;


public abstract class EngineProcess {
	
	public abstract JDMessage process(JDMessage msg);
}
