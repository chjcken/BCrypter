package com.kiss.aes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public class AES {

	private Cipher encrypt;
	private Cipher decrypt;
	//private SecretKeySpec secretKey;

	private final byte[] initialization_vector = "qwertyuiopasdfgh".getBytes();
	private static final AES instance = new AES();
	
	public static AES getInstance(){
		return instance;
	}
	
	private AES() {
		init("this is aes secret key");
	}

	public void init(String encryptionKey) {

		try {
			//SecretKeyFactory factory = SecretKeyFactory.getInstance("AES");
			//secretKey = new SecretKeySpec(key.getBytes(), "AES");//DESKeySpec(key.getBytes());
			//secretKey = factory.generateSecret(keySpec);
			
			
			
			
			IvParameterSpec alogrithm_specs = new IvParameterSpec(
					initialization_vector);
			
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
		    cipher.init(Cipher.ENCRYPT_MODE, key, alogrithm_specs);
			
		    encrypt = cipher;
		    
		    Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		    cipher2.init(Cipher.DECRYPT_MODE, key, alogrithm_specs);
		    
		    decrypt = cipher2;
			

//			// set encryption mode ...
//			encrypt = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
//			encrypt.init(Cipher.ENCRYPT_MODE, secretKey, alogrithm_specs);
//
//			// set decryption mode
//			decrypt = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
//			decrypt.init(Cipher.DECRYPT_MODE, secretKey, alogrithm_specs);

		} catch (Exception e) {
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