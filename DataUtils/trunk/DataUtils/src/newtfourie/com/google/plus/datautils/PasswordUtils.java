package newtfourie.com.google.plus.datautils;

public class PasswordUtils {

	/**********************************************************/
	/*
	 * Singleton
	 */
	/*********************************************************/
	
	private PasswordUtils()
	{
		
	}
	
	private static PasswordUtils _thisObj = null;
	
	public static PasswordUtils getInstance()
	{
		if (_thisObj == null)
		{
			_thisObj = new PasswordUtils();
		}
		
		return _thisObj;
	}
	
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Methods
	 */
	/*********************************************************/
	
	/**
	 * Will attempt to encrypt the provided password, will return null if there is a problem
	 * @param password - the password to encrypt
	 * @return an encrypted password or null if failure to encrypt
	 */
	public String encryptPassword(String password)
	{
		return DataEncryptor.getInstance().encrypt(password);
	}
	
	/**
	 * Will attempt to encrypt and url encode the provided password, will return null if there is a problem
	 * @param password - the password to encrypt
	 * @return an encrypted password or null if failure to encrypt or encode
	 */
	public String encryptAndEncodePassword(String password)
	{
		String toReturn = DataEncryptor.getInstance().encrypt(password);
		toReturn = StringEncoder.getInstance().UrlEncode(toReturn);
		return toReturn;
	}
	
	/**********************************************************/
	/*********************************************************/
	
}
