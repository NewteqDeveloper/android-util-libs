package newtfourie.com.google.plus.contactmanager;

import java.io.IOException;
import java.io.OutputStream;

import newtfourie.com.google.plus.easytoast.EasyToast;

import android.content.ContentUris;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

public class InsertNewContactPicture {

	byte[] picture;
	Context context;
	
	private static final String TAG = "InsertNewContactPicture";
	
	/**
	 * A constructor to set the picture and context being used
	 * @param context - where you called this method from
	 * @param picture - the picture to set as a contact picture
	 */
	public InsertNewContactPicture(Context context, byte[] picture)
	{
		this.picture = picture;
		this.context = context;
	}
	
	/**
	 * This method will attempt to save the contact picture to the device
	 * @param uri - the contact's URI to write the picture to
	 */
	public void tryWriteProfilePicture(Uri uri)
	{
    	Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
        	long id = cursor.getLong(cursor.getColumnIndex(Contacts._ID));
			setContactProfilePicture(id, picture);
		}
        
        cursor.close();
	}
	
	private boolean setContactProfilePicture(long rawContactId, byte[] picture)
	{
		Uri pictureUri = Uri.withAppendedPath(ContentUris.withAppendedId(RawContacts.CONTENT_URI, rawContactId), RawContacts.DisplayPhoto.CONTENT_DIRECTORY);
		
		try
		{
			AssetFileDescriptor assetDescriptor = context.getContentResolver().openAssetFileDescriptor(pictureUri, "rw");
			OutputStream outputStream = assetDescriptor.createOutputStream();
			outputStream.write(picture);
			outputStream.close();
			assetDescriptor.close();
			return true;
		}
		catch (IOException ex)
		{
			EasyToast.toastShort(context, "Error. Profile picture for user unable to be set");
			Log.e(TAG, "IO exception occured");
			Log.e(TAG, ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		
		return false;
		
	}
	
}
