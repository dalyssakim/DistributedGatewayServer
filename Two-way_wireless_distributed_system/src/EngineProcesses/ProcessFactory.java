package EngineProcesses;
import Interface.Job;
import Message.JDMessage;
import Message.JDMessageType;


public class ProcessFactory {

	public static EngineProcess EngineProcess(int type) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		
		switch (type){
		case   JDMessageType.pullInterface : return new processSendInterface();
		case   JDMessageType.halt : return new processHalt();
		case   JDMessageType.pushResult : return new processAcceptResult();
		
		}
		
		return null;
	}

}
