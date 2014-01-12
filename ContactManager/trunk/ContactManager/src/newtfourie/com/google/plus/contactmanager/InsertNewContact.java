package newtfourie.com.google.plus.contactmanager;

import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Intents;

public class InsertNewContact extends ModifyPhoneContacts {
	
	/**
	 * The constructor which will set the provided information
	 * @param fullName - the user's full name
	 * @param phoneNumber - the user's tel number
	 * @param company - the user's company
	 * @param email - the user's email
	 * @param note - a note for where this user was met
	 */
	public InsertNewContact(String fullName, String phoneNumber, String company, String email, String note)
	{
		super(fullName, phoneNumber, company, email, note);
	}
	
	@Override
	public Intent getSaveContactIntent()
	{
		Intent intent = new Intent(Intents.Insert.ACTION);
		intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
		
		//Setting the name and surname
		intent.putExtra(Intents.Insert.NAME, fullName);
		//Setting the phone number
		intent.putExtra(Intents.Insert.PHONE_TYPE, CommonDataKinds.Phone.TYPE_WORK);
		intent.putExtra(Intents.Insert.PHONE, phoneNumber);
		//Setting the company
		intent.putExtra(Intents.Insert.COMPANY, company);
		//Setting the email
		intent.putExtra(Intents.Insert.EMAIL_TYPE, CommonDataKinds.Email.TYPE_WORK);
		intent.putExtra(Intents.Insert.EMAIL, email);
		
		//Adding a note for which event they are at
		intent.putExtra(Intents.Insert.NOTES, note);
		
		//force return to this app when contact is saved
		intent.putExtra("finishActivityOnSaveCompleted", true);
		
		return intent;
	}
	
}
