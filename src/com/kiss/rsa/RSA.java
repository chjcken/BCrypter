package com.kiss.rsa;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

import com.kiss.md5.MD5;


public class RSA {
	private Cipher cipher = null;
	private final String ALGORITHM = "RSA";
	private final String PRIVATE_KEY_FILE = "private.key";
	private final String PUBLIC_KEY_FILE = "public.key";
	private static final RSA instance = new RSA();
	
	public static RSA getInstance(){
		return instance;
	}
	
	private RSA() {
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
	public void generateKeyPair(int length, String path) throws NoSuchAlgorithmException, IOException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
		keyGen.initialize(length);
		KeyPair key = keyGen.generateKeyPair();
		
		FileOutputStream fos = new FileOutputStream(path + PUBLIC_KEY_FILE);
		fos.write(key.getPublic().getEncoded());
		fos.close();
		
		fos = new FileOutputStream(path + PRIVATE_KEY_FILE);
		fos.write(key.getPrivate().getEncoded());
		fos.close();
		
	}

	public Key readPublicKey(String keyName) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
		byte[] keyByteArray = Files.readAllBytes(new File(keyName).toPath());
		Key publicKey = 
			    KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(keyByteArray));
		return publicKey;
	}
	
	public Key readPrivateKey(String keyName) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException{
		byte[] keyByteArray = Files.readAllBytes(new File(keyName).toPath());
		Key privateKey = 
				KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(keyByteArray));
		return privateKey;
	}

	public void encrypt(Key key, InputStream input, OutputStream output)
			throws InvalidKeyException, IOException {
//		cipher.init(Cipher.ENCRYPT_MODE, key);
//		output = new CipherOutputStream(output, cipher);
		
		output = getCipherOutputStream(key, output);
		
		writeBytes(input, output);
	}

	public void decrypt(Key key, InputStream input, OutputStream output)
			throws IOException, InvalidKeyException {

//		cipher.init(Cipher.DECRYPT_MODE, key);
//		input = new CipherInputStream(input, cipher);
		
		input = getCipherInputStream(key, input);
		
		writeBytes(input, output);
	}

	private void writeBytes(InputStream input, OutputStream output)
			throws IOException {
		byte[] writeBuffer = new byte[1024];
		int readBytes = 0;

		while ((readBytes = input.read(writeBuffer)) >= 0) {
			output.write(writeBuffer, 0, readBytes);
		}
		
		output.close();
		input.close();
	}
	
	public void sign(Key key, InputStream input, OutputStream output) throws InvalidKeyException, IOException{
//		cipher.init(Cipher.ENCRYPT_MODE, key);
//		output = new CipherOutputStream(output, cipher);
		
		output = getCipherOutputStream(key, output);
		
		byte[] hashByte = MD5.getInstance().md5(input);
		
		output.write(hashByte);
		output.close();
	}
	
	public boolean verify(Key key, InputStream signedInput, InputStream originalInput) throws InvalidKeyException, IOException{
		signedInput = getCipherInputStream(key, signedInput);
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int readBytes = 0;
		byte[] data = new byte[1024];

		while ((readBytes = signedInput.read(data, 0, data.length)) != -1) {
		  buffer.write(data, 0, readBytes);
		}

		buffer.flush();
		
		byte[] signedHashByte = buffer.toByteArray();
		byte[] orginalHashByte = MD5.getInstance().md5(originalInput);
		
		return MD5.getInstance().compare(signedHashByte, orginalHashByte);
	}
	
	private InputStream getCipherInputStream(Key key, InputStream input) throws InvalidKeyException{
		cipher.init(Cipher.DECRYPT_MODE, key);
		input = new CipherInputStream(input, cipher);
		
		return input;
	}
	
	private OutputStream getCipherOutputStream(Key key, OutputStream output) throws InvalidKeyException{
		cipher.init(Cipher.ENCRYPT_MODE, key);
		output = new CipherOutputStream(output, cipher);
		
		return output;
	}
	
	
	
	
	
}
