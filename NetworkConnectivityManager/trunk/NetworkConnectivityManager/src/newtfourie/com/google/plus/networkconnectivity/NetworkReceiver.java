package newtfourie.com.google.plus.networkconnectivity;

import newtfourie.com.google.plus.easytoast.EasyToast;
import newtfourie.com.google.plus.networkconnectivitymanager.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NetworkReceiver extends BroadcastReceiver {

	//This needs to be a singleton according to google documentation (or should be, idk)
	private NetworkReceiver()
	{
		
	}
	
	private static NetworkReceiver thisObj = null;
	
	public static NetworkReceiver getInstance()
	{
		if (thisObj == null)
		{
			thisObj = new NetworkReceiver();
		}
		
		return thisObj;
	}
	//end singleton
	
	private static final String TAG = "NetworkReceiver";
	
	/**
	 * Handle received broadcast - this method will be called if there is a change on the network; such as network connectivity loss
	 * @param context
	 * @param intent
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		
		//Check if user is still connected, if not, so a toast
		if (NetworkControl.getInstance(context).isConnected())
		{
			Log.d(TAG, "User is now connected to a network");
			EasyToast.toastLong(context, context.getString(R.string.connection_established));
		}
		else
		{
			Log.d(TAG, "User is now disconnected from a network");
			EasyToast.toastLong(context, context.getString(R.string.connection_lost));
		}
		
	}
	
}
