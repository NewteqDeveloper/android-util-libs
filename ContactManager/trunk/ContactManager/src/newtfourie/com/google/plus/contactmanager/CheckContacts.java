package newtfourie.com.google.plus.contactmanager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class CheckContacts {

	/**
	 * This method will return a contact uri of a contact that already exists in the phone directory. If there is no contact
	 * that matches the incoming number then the result will be null
	 * @param incommingNumber - which number to check if it already exists in the phone directory
	 * @param context - where you are calling this method from (which activity)
	 * @return the contact uri if there is already a contact on the phone with the same number, returns null otherwise.
	 */
	public static boolean isContact(Context context, String incommingNumber) {
        Cursor cursor = null;
        
        try
        {
        	Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(incommingNumber));
            //cursor = this.getContentResolver().query(uri, new String[] { Contacts.LOOKUP_KEY, Contacts._ID}, null, null, null);
        	cursor = context.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst())
            {
            	return true;
            }
        }
        finally
        {
            if(cursor!=null)
            {
                cursor.close();
            }
        }
        
        return false;
    }
	
	/**
	 * BACK UP
	 */
	 /* public static boolean isContact(String incommingNumber, Context context) {
	    Cursor cursor = null;
	    
	    try
	    {
	    	Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(incommingNumber));
	        //cursor = this.getContentResolver().query(uri, new String[] { Contacts.LOOKUP_KEY, Contacts._ID}, null, null, null);
	    	cursor = context.getContentResolver().query(uri, null, null, null, null);
	        if (cursor != null && cursor.moveToFirst())
	        {
	        	int lookupKeyIndex = cursor.getColumnIndex(Contacts.LOOKUP_KEY);
	        	String currentLookupKey = cursor.getString(lookupKeyIndex);
	        	int idLookup = cursor.getColumnIndex(Contacts._ID);
	        	long currentId = cursor.getLong(idLookup);
	        	
	        	//Cursor rawContact = getContentResolver().query(RawContacts.CONTENT_URI, null, Data.CONTACT_ID + " = ?" new String, selectionArgs, sortOrder)
	        	
	        	//create returning uri
	        	Uri foundContact = Contacts.getLookupUri(currentId, currentLookupKey);
	        	return foundContact;
	        	
	        	return true;
	        }
	    }
	    finally
	    {
	        if(cursor!=null)
	        {
	            cursor.close();
	        }
	    }
	    
	    return false;
	}*/
}
