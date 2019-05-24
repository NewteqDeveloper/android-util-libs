package newtfourie.com.google.plus.datautils.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;
import android.util.Log;

/***************************************************************
* Title: AES 128bit Cross Platform (Java and C#) Encryption Compatibility
* Author: Joseph Ssenyange
* Date: 2011 September 21
* Code version: 1
* Availability: http://zenu.wordpress.com/2011/09/21/aes-128bit-cross-platform-java-and-c-encryption-compatibility/
***************************************************************/

/**
 * Encryption code gathered from:
 * http://zenu.wordpress.com/2011/09/21/aes-128bit-cross-platform-java-and-c-encryption-compatibility/
 * @author Joseph Ssenyange
 */
public class DataEncryptor {
	
	
	/**********************************************************/
	/*
	 * Singleton
	 */
	/*********************************************************/
	
	private DataEncryptor()
	{
		
	}
	
	private static DataEncryptor _thisObj = null;
	
	public static DataEncryptor getInstance()
	{
		if (_thisObj == null)
		{
			_thisObj = new DataEncryptor();
		}
		
		_thisObj.setCharacterEncoding("UTF-8");
		_thisObj.setCipherTransformation("AES/CBC/PKCS5Padding");
		_thisObj.setAesEncryptionAlgorithm("AES");
		_thisObj.setKEY("1nfin1ty$crip7");
		
		return _thisObj;
	}
	
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Attributes + Properties
	 */
	/*********************************************************/
	
	private String characterEncoding;
    private String cipherTransformation;
    private String aesEncryptionAlgorithm;
    private String key;
    
    public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public String getCipherTransformation() {
		return cipherTransformation;
	}

	public void setCipherTransformation(String cipherTransformation) {
		this.cipherTransformation = cipherTransformation;
	}

	public String getAesEncryptionAlgorithm() {
		return aesEncryptionAlgorithm;
	}

	public void setAesEncryptionAlgorithm(String aesEncryptionAlgorithm) {
		this.aesEncryptionAlgorithm = aesEncryptionAlgorithm;
	}

	public String getKEY() {
		return key;
	}

	public void setKEY(String Key) {
		this.key = Key;
	}
	
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Methods
	 */
	/*********************************************************/

	/**
     * Encrypts plaintext using AES 128bit key and a Chain Block Cipher and returns a base64 encoded string
     * @param plainText Plain text to encrypt
     * @return Base64 encoded string
     */
    public String encrypt(String plainText) {
        try
        {
        	byte[] plainTextbytes = plainText.getBytes(characterEncoding);
        	byte[] keyBytes = getKeyBytes(key);
            return Base64.encodeToString(encrypt(plainTextbytes,keyBytes, keyBytes), Base64.DEFAULT);
        }
        catch (InvalidKeyException e) {
			Log.e("EncryptPassword_InvalidKeyException", "Unable to encrypt password");
			return null;
		}
		catch (UnsupportedEncodingException e) {
			Log.e("EncryptPassword_UnsupportedEncodingException", "Unable to encrypt password");
			return null;
		}
		catch (NoSuchAlgorithmException e) {
			Log.e("EncryptPassword_NoSuchAlgorithmException", "Unable to encrypt password");
			return null;
		} 
		catch (NoSuchPaddingException e) {
			Log.e("EncryptPassword_NoSuchPaddingException", "Unable to encrypt password");
			return null;
		}
		catch (InvalidAlgorithmParameterException e) {
			Log.e("EncryptPassword_InvalidAlgorithmParameterException", "Unable to encrypt password");
			return null;
		}
		catch (IllegalBlockSizeException e) {
			Log.e("EncryptPassword_IllegalBlockSizeException", "Unable to encrypt password");
			return null;
		}
		catch (BadPaddingException e) {
			Log.e("EncryptPassword_BadPaddingException", "Unable to encrypt password");
			return null;
		}
        
    }
 
    private byte[] encrypt(byte[] plainText, byte[] key, byte [] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        plainText = cipher.doFinal(plainText);
        return plainText;
    }
 
    private byte[] getKeyBytes(String key) throws UnsupportedEncodingException{
        byte[] keyBytes= new byte[16];
        byte[] parameterKeyBytes= key.getBytes(characterEncoding);
        System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        return keyBytes;
    }
    
    /**********************************************************/
	/*********************************************************/
}
