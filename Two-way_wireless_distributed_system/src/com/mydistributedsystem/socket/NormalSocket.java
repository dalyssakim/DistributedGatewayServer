package com.mydistributedsystem.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NormalSocket extends AbstractSocket {
	public NormalSocket() {
	}

	public void init(byte[] key) {
	}

	public InputStream getInputStream() throws IOException {
		return socket.getInputStream();
	}

	public OutputStream getOutputStream() throws IOException {
		return socket.getOutputStream();
	}
}