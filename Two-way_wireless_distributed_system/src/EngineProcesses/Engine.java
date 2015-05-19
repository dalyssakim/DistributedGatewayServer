package EngineProcesses;
import Interface.JobFactory;
import Message.JDMessage;
import Nodes.Node;


public class Engine {

	private EngineProcess engine;
	private JDMessage msg;
	public Engine(JDMessage msg){
		this.msg = msg;
		try {
			engine = ProcessFactory.EngineProcess(msg.type);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public JDMessage process(){
		JDMessage nmsg = engine.process(msg);
		return nmsg;
	}
	
}
