package com.unihyr.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class StringEncryption
{
	public static String encrypt(String text) {
	  
	    	         try{
    	        	 String key = "Bar12345Bar12345"; // 128 bit key
	    	         // Create key and cipher
	    
	    	         Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	    
	    	         Cipher cipher = Cipher.getInstance("AES");
	   
	    	 
	  
	    	         // encrypt the text
	   
	    	         cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	    
	    	         byte[] encrypted = cipher.doFinal(text.getBytes());
	   
					return new String(encrypted);
	    	         }catch(Exception e){
	    	        	 e.printStackTrace();
	    	        	 return null;
	    	         }
	    	 
	    
	    	       
	}
	public static String decrypt(byte[] encrypted) {
		  try
		{
			// decrypt the text
			  String key = "Bar12345Bar12345"; // 128 bit key
			   
				 
			    
			     // Create key and cipher

			     Key aesKey = new SecretKeySpec(key.getBytes(), "AES");

			     Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);

			String decrypted = new String(cipher.doFinal(encrypted));

			return decrypted;
		} catch (InvalidKeyException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return null;
	}
}
