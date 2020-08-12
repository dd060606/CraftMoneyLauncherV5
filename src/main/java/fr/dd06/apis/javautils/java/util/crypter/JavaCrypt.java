package fr.dd06.apis.javautils.java.util.crypter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class JavaCrypt {

	
	 private SecretKeySpec secretKey;
	 private byte[] key;
	 
	 private String cryptKey;
	    
	 private void setKey(String cryptkey) 
	    {
	        MessageDigest sha = null;
	        try {
	            key = cryptkey.getBytes("UTF-8");
	            sha = MessageDigest.getInstance("SHA-1");
	            key = sha.digest(key);
	            key = Arrays.copyOf(key, 16); 
	            secretKey = new SecretKeySpec(key, "AES");
	        } 
	        catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } 
	        catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    public String encrypt(String toEncrypt) 
	    {
	        try
	        {
	        	setKey(this.getCryptKey());
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	            return Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes("UTF-8")));
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 
	    public String decrypt(String toDecrypt) 
	    {
	        try
	        {
	            setKey(this.getCryptKey());
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	            cipher.init(Cipher.DECRYPT_MODE, secretKey);
	            return new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt)));
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	        return null;
	    }


		public String getCryptKey() {
			if(cryptKey == null) {
				cryptKey = "default";
			}
			
			return cryptKey;
			
		}

		public void setCryptKey(String cryptKey) {
			this.cryptKey = cryptKey;
		}
}
