package com.mydistributedsystem.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;

public abstract class AbstractSocket {
	protected Socket socket;
	protected Key key;

	public void init(Socket socket) throws UnknownHostException, IOException {
		this.socket = socket;
	}

	public void init(String host, int port) throws UnknownHostException,
			IOException {
		socket = new Socket(host, port);
	}

	protected abstract void init(byte[] key) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException,
			InvalidKeySpecException, InvalidAlgorithmParameterException;

	public abstract InputStream getInputStream() throws IOException;

	public abstract OutputStream getOutputStream() throws IOException;
}