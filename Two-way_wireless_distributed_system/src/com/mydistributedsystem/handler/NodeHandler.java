package com.mydistributedsystem.handler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.mydistributedsystem.interfaces.Job;
import com.mydistributedsystem.message.JDMessage;
import com.mydistributedsystem.nodes.Node;
import com.mydistributedsystem.serverengine.Engine;


public class NodeHandler extends Handler{

	public NodeHandler(Socket socket){
		super(socket);
	}



	@Override
	public void handle(JDMessage msg) {
		// TODO Auto-generated method stub
		try {
			out.write(serialize(new Engine(msg).process()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
