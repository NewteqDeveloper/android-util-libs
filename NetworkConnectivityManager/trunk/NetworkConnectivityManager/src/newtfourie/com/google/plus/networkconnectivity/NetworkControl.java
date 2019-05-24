package newtfourie.com.google.plus.networkconnectivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * This class will access the network and provide feedback on it
 * @author Newt
 *
 */

public class NetworkControl {

	/**********************************************************/
	/*
	 * Singleton
	 */
	/*********************************************************/
	
	private NetworkControl()
	{
		
	}
	
	private static NetworkControl _thisObj = null;
	
	public static NetworkControl getInstance(Context currentContext)
	{
		if (_thisObj == null)
		{
			_thisObj = new NetworkControl();
		}
		
		context = currentContext;
		
		return _thisObj;
	}
	
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Attributes
	 */
	/*********************************************************/
	
	private static Context context;
	private static final String TAG = "NetworkControl";
	
	/**********************************************************/
	/*********************************************************/
	
	/**********************************************************/
	/*
	 * Methods
	 */
	/*********************************************************/
	
	/**
	 * Checks if the user is connected to a data network
	 * @return true if they are connected to a data network
	 */
	public boolean isConnected()
	{
		ConnectivityManager connectMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectMan.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected())
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if the user is connected to a data network - in particular: WiFI
	 * @return true if they are connected to a data network - in particular: WiFI
	 */
	public boolean isConnectedToWiFi()
	{
		ConnectivityManager connectMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (netInfo != null && netInfo.isConnected())
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if the user is connected to a data network - in particular: mobile data
	 * @return true if they are connected to a data network - in particular: mobile data
	 */
	public boolean isConnectedToMobileData()
	{
		ConnectivityManager connectMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (netInfo != null && netInfo.isConnected())
			return true;
		else
			return false;
	}
	
	/**
	 * Make sure that this is only called when you have got the correct permissions in the manifest
	 * and that you have asked the user for permission. The application will crash if you do not
	 * have the correct permissions in the manifest.
	 * 
	 * This will enable the user's mobile data
	 */
	public boolean enableMobileData()
	{
	    try
	    {
			final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    final Class<?> conmanClass = Class.forName(conman.getClass().getName());
		    final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
		    connectivityManagerField.setAccessible(true);
		    final Object connectivityManager = connectivityManagerField.get(conman);
		    final Class<?> connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
		    final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
		    setMobileDataEnabledMethod.setAccessible(true);
		    
		    setMobileDataEnabledMethod.invoke(connectivityManager, true);
		    
		    return true;
	    }
	    catch (Exception ex)
	    {
	    	Log.e(TAG, ex.getLocalizedMessage());
	    	ex.printStackTrace();
	    	return false;
	    }
	}
	
	/**
	 * Make sure that this is only called when you have got the correct permissions in the manifest
	 * and that you have asked the user for permission. The application will crash if you do not
	 * have the correct permissions in the manifest.
	 * 
	 * This will disable the user's mobile data
	 */
	public boolean disableMobileData()
	{
	    try
	    {
			final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    final Class<?> conmanClass = Class.forName(conman.getClass().getName());
		    final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
		    connectivityManagerField.setAccessible(true);
		    final Object connectivityManager = connectivityManagerField.get(conman);
		    final Class<?> connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
		    final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
		    setMobileDataEnabledMethod.setAccessible(true);
		    
		    setMobileDataEnabledMethod.invoke(connectivityManager, false);
		    
		    return true;
	    }
	    catch (Exception ex)
	    {
	    	Log.e(TAG, ex.getLocalizedMessage());
	    	ex.printStackTrace();
	    	return false;
	    }
	}
	
	/**
	 * Make sure that this is only called when you have got the correct permissions in the manifest
	 * and that you have asked the user for permission. The application will crash if you do not
	 * have the correct permissions in the manifest
	 * 
	 * This will disconnect the user's wifi
	 */
	public void disconnectWifi()
	{
		 WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		 wifi.disconnect();
	}
	
	/**
	 * Make sure that this is only called when you have got the correct permissions in the manifest
	 * and that you have asked the user for permission. The application will crash if you do not
	 * have the correct permissions in the manifest
	 * 
	 * This will reconnect the user's wifi
	 */
	public void reconnectWifi()
	{
		 WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		 wifi.reconnect();
	}
	
	/**********************************************************/
	/*
	 * Network receiver actions
	 * 
	 * These methods are to be called in the onCreate() and onDestory() methods of the
	 * activities that want to use network data
	 */
	/*********************************************************/
	
	/**
	 * Must be called in the onCreate() of the activity that wants to use network data
	 * The reason being is that it must be unregistered in the onDestory() as well,
	 * this will ensure that the network receiver does not receive broadcasts even if the app
	 * if closed
	 */
	public void registerNetworkReceiver()
	{
		// Registers BroadcastReceiver to track network connection changes.
		
		//Create the intent filter
        IntentFilter networkChangesFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        
        //Register the filter with the broadcast manager
        LocalBroadcastManager.getInstance(context).registerReceiver(NetworkReceiver.getInstance(), networkChangesFilter);
	}
	
	/**
	 * This must be called in the onDestory() of the activity that registered the receiver.
	 * This will ensure that the network receiver does not receive broadcasts even if the app
	 * if closed
	 */
	public void unregisterNetworkReceiver()
	{
		//Unregister the network changes receiver with the broadcast manager
		LocalBroadcastManager.getInstance(context).unregisterReceiver(NetworkReceiver.getInstance());
	}
	
	/**********************************************************/
	/*********************************************************/
	
}
