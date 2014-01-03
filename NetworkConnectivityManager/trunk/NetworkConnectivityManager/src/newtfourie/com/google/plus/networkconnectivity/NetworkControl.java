package newtfourie.com.google.plus.networkconnectivity;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;

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
