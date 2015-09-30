package com.kiss.md5;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5 {
	private MessageDigest md;
	
	private static final MD5 instance = new MD5();
	
	public static MD5 getInstance(){
		return instance;
	}
	
	private MD5(){
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] md5(InputStream is) throws IOException{
		md.reset();
		byte[] dataBuffer = new byte[1024];
		int readBytes = 0;
		
		while ((readBytes = is.read(dataBuffer)) >= 0){
			md.update(dataBuffer, 0, readBytes);
		}		
		
		return md.digest();
	}
	
	public String md5InHex(InputStream is) throws IOException{
		byte[] mdBytes = md5(is);
		
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdBytes.length; i++) {
          sb.append(Integer.toString((mdBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        
		return sb.toString();
	}
	
	public boolean compare(byte[] fileOne, byte[] fileTwo){				
		return Arrays.equals(fileOne, fileTwo);
	}
}
