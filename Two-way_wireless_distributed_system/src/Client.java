import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.crypto.NoSuchPaddingException;

import com.mydistributedsystem.interfaces.Job;
import com.mydistributedsystem.message.JDMessage;
import com.mydistributedsystem.message.JDMessageType;
import com.mydistributedsystem.socket.AbstractSocket;
import com.mydistributedsystem.socket.DESSocket;
import com.mydistributedsystem.socket.SocketFactory;

public class Client {
	
	final static String INET_ADDR = "127.0.0.1";
	final static int PORT = 50505;
	final static int id = 2;
	
	
	public void SendMSG(ArrayList data, Object result) throws UnknownHostException, InvalidKeyException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, InterruptedException{
		InetAddress address = InetAddress.getByName(INET_ADDR);			
		
		byte[] buf = new byte[1024];

		try{
			Socket clientSocket = new Socket(address, PORT);
			OutputStream outputStream = clientSocket.getOutputStream();
			InputStream inputStream = clientSocket.getInputStream();
			JDMessage dObject = new JDMessage();
			dObject.id=id;
			dObject.type = JDMessageType.pullInterface;
			dObject.data = null;
			outputStream.write(serialize(dObject));
		
			int charsRead = 0;

				System.out.println("Client Sent Message");
				inputStream.read(buf);
				while(buf.length <= 0){
					inputStream.read(buf);
				}

				Object obj = (Object) deserialize(buf);
				dObject = (JDMessage)obj;
				
				clientSocket.close();
				if(dObject.id == 0){
					Socket clientSocket2 = new Socket(address, PORT);
					System.out.println("Message From Server");
					Job job = (Job) dObject.data;
					dObject.id = id;
					dObject.type = JDMessageType.pushResult;
					dObject.data = job.doJob(data);
					//System.out.println(job.doJob(data));
					System.out.println("Sending Result : " + dObject.data);
					outputStream = clientSocket2.getOutputStream();
					outputStream.write(serialize(dObject));
					clientSocket2.close();
				}
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void main(String[] args) throws Throwable {

		ArrayList data = new ArrayList();
		Object result = null;
			Client client = new Client();
			
			for(int i = 0; i < 100; i ++){
				data.add((double)i);
			}
			client.SendMSG(data, result);

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
}
