package com.kiss.des;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DES {

	private Cipher encrypt;
	private Cipher decrypt;
	private SecretKey secretKey;

	private final byte[] initialization_vector = { 22, 33, 11, 44, 55, 99, 66, 77 };
	private static final DES instance = new DES();
	
	public static DES getInstance(){
		return instance;
	}
	
	private DES() {
		init("this is des secret key");
	}

	public void init(String key) {

		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			KeySpec keySpec = new DESKeySpec(key.getBytes());
			secretKey = factory.generateSecret(keySpec);
			AlgorithmParameterSpec alogrithm_specs = new IvParameterSpec(
					initialization_vector);

			// set encryption mode ...
			encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
			encrypt.init(Cipher.ENCRYPT_MODE, secretKey, alogrithm_specs);

			// set decryption mode
			decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
			decrypt.init(Cipher.DECRYPT_MODE, secretKey, alogrithm_specs);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidKeyException | InvalidAlgorithmParameterException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

	}

	public void encrypt(InputStream input, OutputStream output)
			throws IOException {

		output = new CipherOutputStream(output, encrypt);
		writeBytes(input, output);
	}

	public void decrypt(InputStream input, OutputStream output)
			throws IOException {

		input = new CipherInputStream(input, decrypt);
		writeBytes(input, output);
	}

	private void writeBytes(InputStream input, OutputStream output)
			throws IOException {
		byte[] writeBuffer = new byte[512];
		int readBytes = 0;

		while ((readBytes = input.read(writeBuffer)) >= 0) {
			output.write(writeBuffer, 0, readBytes);
		}

		output.close();
		input.close();
	}

}