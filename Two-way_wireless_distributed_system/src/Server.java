import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;

import Interface.Job;
import Socket.AbstractSocket;
import Socket.SocketFactory;


public class Server extends Thread{
	final static String INET_ADDR = "127.0.0.1";
	final static int PORT = 8888;
	public int dpacketsize;
	public String serverName;
	public boolean stopThread = false;
	public byte[] buf = new byte[1024];

	public Server(int dpacketsize, String serverName){
		this.dpacketsize = dpacketsize;
		this.serverName = serverName;
	}
	
	public void run(){
		try{
			
			InetAddress addr = InetAddress.getByName(INET_ADDR);
			
			Message msg = null;
			
			ServerSocket server = new ServerSocket(PORT);
			Socket socket = server.accept();
			
			 AbstractSocket as = SocketFactory.getInstance(socket,
					 "Socket.Normal", "mypassword".getBytes());
					
			System.out.println("["+this.serverName+"] Ready to receive a multicast packet.");
			
			InputStream in = null;
			OutputStream out = null;
			
			
			while(stopThread == false){
				try{
				 in = socket.getInputStream();
				 out = socket.getOutputStream();
					
					in.read(buf);
					if(buf != null){
					Object obj = deserialize(buf);
					msg = (Message)obj;
	
					
					System.out.println("Recv Data ("+this.serverName+") : "+ msg.type);
					Job tmpJob = (Job)msg.data;
				//	tmpJob.doJob();
					out.write(serialize(msg));
					break;
					}
					else {
						System.out.println("DATA NULL...");
					}
				} catch(Exception e){
					e.printStackTrace();
					in.close();
					out.close();
					System.out.println("Connection Out");
					break;
				}
			}

			//socket.close();
		} catch(Exception e){
			e.printStackTrace();
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
	
	public void stopServer(){
		this.stopThread = true;
	}
	
	public void startServer(){
		this.start();
	}
	

}
