package newtfourie.com.google.plus.datautils;

import java.net.URLEncoder;

public class StringEncoder {

	/**********************************************************/
	/*
	 * Singleton
	 */
	/*********************************************************/
	
	private StringEncoder()
	{
		
	}
	
	private static StringEncoder _thisObj = null;
	
	public static StringEncoder getInstance()
	{
		if (_thisObj == null)
		{
			_thisObj = new StringEncoder();
		}
		
		return _thisObj;
	}
	
	/**********************************************************/
	/*********************************************************/
	
	/**
	 * This method will encode the provided string for url transportation, result will be null if error occured in the encoding process
	 * @param details - the string to encode
	 * @return the encoded string, or null if failure to encode
	 */
	public String UrlEncode(String details)
	{
		if (details == null)
		{
			return null;
		}
		DataEncryptor.getInstance().setCharacterEncoding("UTF-8");
		try
		{
			return URLEncoder.encode(details.substring(0, details.lastIndexOf("\n")), DataEncryptor.getInstance().getCharacterEncoding());
		}
		catch (Exception ex)
		{
			return null;
		}
	}
	
}
