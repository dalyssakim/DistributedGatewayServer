package Socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;

public class SocketFactory {

	public static AbstractSocket getInstance(String host, int port,
			String type, byte[] key) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnknownHostException, IOException, InvalidKeyException,
			NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeySpecException, InvalidAlgorithmParameterException {
		Class C = Class.forName(type + "Socket");
		AbstractSocket as = (AbstractSocket) C.newInstance();
		as.init(host, port);
		as.init(key);
		return as;
	}

	public static AbstractSocket getInstance(Socket socket, String type,
			byte[] key) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnknownHostException, IOException,
			InvalidKeyException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException {
		Class C = Class.forName(type + "Socket"); 
		AbstractSocket as = (AbstractSocket) C.newInstance();
		as.init(socket);
		as.init(key);
		return as;
	}
}