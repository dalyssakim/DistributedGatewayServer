package com.mydistributedsystem.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESSocket extends AbstractSocket {
	Cipher inputCipher;
	Cipher outputCipher;

	public void init(byte[] key) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException,
			InvalidKeyException, InvalidAlgorithmParameterException {
		DESKeySpec desKeySpec = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		inputCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		inputCipher.init(Cipher.DECRYPT_MODE, secretKey);
		outputCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		outputCipher.init(Cipher.ENCRYPT_MODE, secretKey);
	}

	public InputStream getInputStream() throws IOException {
		return new CipherInputStream(socket.getInputStream(), inputCipher);
	}

	public OutputStream getOutputStream() throws IOException {
		return new CipherOutputStream(socket.getOutputStream(), outputCipher);
	}
}