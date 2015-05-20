package Handler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import EngineProcesses.Engine;
import Interface.Job;
import Message.JDMessage;
import Nodes.Node;


public class NodeHandler extends Thread{

	private String serverName = "JDServer";
	private byte[] buf = new byte[1024];
	private JDMessage msg;
	private Socket socket;
	public NodeHandler(Socket socket){
		this.socket = socket;
	}
	public void run() {
		// TODO Auto-generated method stub
		InputStream in = null;
		OutputStream out = null;
			try{
			 in = socket.getInputStream();
			 out = socket.getOutputStream();
				
				in.read(buf);
				if(buf != null){
				Object obj = deserialize(buf);
				msg = (JDMessage)obj;
			
				out.write(serialize(new Engine(msg).process()));

				}
				else {
					System.out.println("DATA NULL...");
				}
			} catch(Exception e){
				e.printStackTrace();
		
				System.out.println("Connection Out");

			} finally{
				try {
					in.close();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	
	
	public static byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	
	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
	    ByteArrayInputStream in = new ByteArrayInputStream(data);
	    ObjectInputStream is = new ObjectInputStream(in);
	    return is.readObject();
	}

	public void startHandler(){
		this.start();
	}
	
}
