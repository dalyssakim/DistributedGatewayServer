import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import Handler.NodeHandler;
import Interface.Job;
import Socket.AbstractSocket;
import Socket.SocketFactory;


public class JDModule extends Thread{

	
	final static String INET_ADDR = "127.0.0.1";
	final static int PORT = 50505;
	public int dpacketsize;
	public String serverName;
	public boolean stopThread = false;
	public byte[] buf = new byte[1024];

	public JDModule(int dpacketsize, String serverName){
		this.dpacketsize = dpacketsize;
		this.serverName = serverName;
	}
	
	public void run(){
		
		ServerSocket server = null;
		
		try{
			
			 server = new ServerSocket(PORT);
			while(stopThread == false){

				Socket socket = server.accept();
			
				NodeHandler handler = new NodeHandler(socket);
				handler.start();
				
			}

			//socket.close();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stopServer(){
		this.stopThread = true;
	}
	
	public void startServer(){
		this.start();
	}
	

}

