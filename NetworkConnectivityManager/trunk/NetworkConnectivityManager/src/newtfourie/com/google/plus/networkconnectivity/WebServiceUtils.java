package newtfourie.com.google.plus.networkconnectivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;

import android.util.Log;

public class WebServiceUtils {

	/**********************************************************/
	/*
	 * Singleton
	 */
	/*********************************************************/
	
	/**
	 * This constructor has been made protected so that this class can be extended;
	 * it remains a singleton
	 */
	protected WebServiceUtils()
	{
		
	}
	
	private static WebServiceUtils _thisObj = null;
	
	public static WebServiceUtils getInstance()
	{
		if (_thisObj == null)
		{
			_thisObj = new WebServiceUtils();
		}
		
		return _thisObj;
	}
	
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Attributes
	 */
	/*********************************************************/
	
	private static final int TimeoutPeriod = 5 * 1000; //5 seconds
	public static final String characterEncoding = "UTF-8";
	private static final String TAG = "WebServiceUtils";
	
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Methods
	 */
	/*********************************************************/
	
	/**
	 * This method will query the web service for the json string, if there is any problem the result will be null
	 * @param webServiceUrl
	 * @return a json string, or null if there is a problem
	 */
	public String getJsonString(String webServiceUrl)
	{
		try
		{
			String type = webServiceUrl.substring(0, webServiceUrl.indexOf(":"));
			
			if (type.equals("http"))
			{
				return getHttpJsonString(webServiceUrl);
			}
			else if (type.equals("https"))
			{
				return getHttpsJsonString(webServiceUrl);
			}
			else
			{
				Log.e(TAG, "invalid url. Url provided: " + webServiceUrl + ". Please check that the url begins with http or https");
				return null;
			}
		}
		catch (IndexOutOfBoundsException ex)
		{
			Log.e(TAG, "invalid url. Url provided: " + webServiceUrl + ". Please check that the url begins with http or https");
			return null;
		}
	}
	
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Helpers
	 */
	/*********************************************************/
	
	/**
	 * Helper method to actually connect to an http web service to retreive the json object
	 * @param webServiceUrl - the url to connect to
	 * @return the json object returned by the web service
	 */
	private String getHttpJsonString(String webServiceUrl)
	{
		StringWriter stringWriter = new StringWriter();
		InputStream inputStream = null;
		HttpURLConnection connection = null;
		
		try
		{
			//Setup the connection to the web address
			URL url = new URL(webServiceUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(TimeoutPeriod);
			connection.setRequestMethod("GET");
			
			//get the response code to determine further action
			int responseCode = connection.getResponseCode();
			
			if (responseCode != HttpStatus.SC_OK)
			{
				Log.e(TAG, "Http response: " + responseCode);
				return null;
			}
			else
			{
				inputStream = connection.getInputStream();
				IOUtils.copy(inputStream, stringWriter, characterEncoding);
			}
		}
		catch (Exception ex)
		{
			Log.e(TAG, "Unable to connect to url");
			return null;
		}
		finally
		{
			if (inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch (IOException ex)
				{
					Log.e(TAG, "Unable to close IO stream");
				}
			}
			
			if (connection != null)
			{
				connection.disconnect();
			}
				
		}
		
		//Return result
		String json = stringWriter.toString();
		return json;
	}
	
	/**
	 * Helper method to actually connect to an https web service to retreive the json object
	 * @param webServiceUrl - the url to connect to
	 * @return the json object returned by the web service
	 */
	private String getHttpsJsonString(String webServiceUrl)
	{
		StringWriter stringWriter = new StringWriter();
		InputStream inputStream = null;
		HttpsURLConnection connection = null;
		
		try
		{
			//Setup the connection to the web address
			URL url = new URL(webServiceUrl);
			connection = (HttpsURLConnection) url.openConnection();
			connection.setConnectTimeout(TimeoutPeriod);
			connection.setRequestMethod("GET");
			
			//get the response code to determine further action
			int responseCode = connection.getResponseCode();
			
			if (responseCode != HttpStatus.SC_OK)
			{
				Log.e(TAG, "Http response: " + responseCode);
				return null;
			}
			else
			{
				inputStream = connection.getInputStream();
				IOUtils.copy(inputStream, stringWriter, characterEncoding);
			}
		}
		catch (Exception ex)
		{
			Log.e(TAG, "Unable to connect to url");
			return null;
		}
		finally
		{
			if (inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch (IOException ex)
				{
					Log.e(TAG, "Unable to close IO stream");
				}
			}
			
			if (connection != null)
			{
				connection.disconnect();
			}
				
		}
		
		//Return result
		String json = stringWriter.toString();
		return json;
	}
	
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Memory Clean up
	 */
	/*********************************************************/
	
	public void dispose()
	{
		_thisObj = null;
	}
	
	/**********************************************************/
	/*********************************************************/
	
}
