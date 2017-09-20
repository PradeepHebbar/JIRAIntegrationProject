package com.bms.rwr.utilities;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class EncryptJiraCredentials {
	public static byte[] encrypt(String strClearText) throws Exception {
		String strData = "";
		try {
			Key aesKey = new SecretKeySpec(strClearText.getBytes(), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey); 
			   byte[] inputBytes = strClearText.getBytes(); 
			   return cipher.doFinal(inputBytes);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		//return strData;
	}


	public static String decrypt(String strEncrypted) throws Exception {
		String strData = "";
		try {			
			strData = new String(Base64.getDecoder().decode(strEncrypted.getBytes()));
			strData = new String(Base64.getDecoder().decode(strData.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return strData;
	}

	public static void main(String[] args) throws Exception {
		String Original_text = "Brillio@123";
		System.out.println("Original text :"+ Original_text);
		System.out.println("Original text Length :"+ Original_text.length());		
		/*String enc = encrypt(Original_text);
		System.out.println("Encrypted text: "+ enc);
		String dec = decrypt(enc);
		System.out.println("Decrypted text: "+ dec);*/
		
		
		byte[] byteArray = Original_text.getBytes();;
		System.out.println("Byte array :"+ byteArray);
		String newString = new String(byteArray, "UTF-8");
		System.out.println("String converted byte array :"+ newString);
		System.out.println("String converted byte array Length :"+ newString.length());		
		System.out.println("byte array converted string :"+ newString.replace(" ", "").getBytes());
		
		
		
		
		
		/*byte[] byteArray = Original_text.getBytes();
		System.out.println("Byte array:"+ byteArray);
		String newString = new String(byteArray);
		System.out.println("String converted byte array :"+ newString);	
		System.out.println("String converted byte array Length :"+ newString.length());			
		System.out.println("byte array converted string :"+ newString.getBytes());*/
	}

}
