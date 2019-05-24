package newtfourie.com.google.plus.easytoast;

import android.content.Context;
import android.widget.Toast;

public class EasyToast {

	/**
	 * This will display the message as a long toast
	 * @param currentActivity - the activity you are calling this from
	 * @param message - the message to display
	 */
	public static void toastLong(Context currentActivity, String message)
	{
		Toast.makeText(currentActivity, message, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * This will display the message as a short toast
	 * @param currentActivity - the activity you are calling this from
	 * @param message - the message to display
	 */
	public static void toastShort(Context currentActivity, String message)
	{
		Toast.makeText(currentActivity, message, Toast.LENGTH_SHORT).show();
	}
	
}
