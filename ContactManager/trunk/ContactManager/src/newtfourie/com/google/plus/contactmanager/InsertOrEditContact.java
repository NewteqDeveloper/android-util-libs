package newtfourie.com.google.plus.contactmanager;

import android.content.Intent;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Intents;

public class InsertOrEditContact extends ModifyPhoneContacts {

	/**
	 * The constructor which will set the provided information
	 * @param fullName - the user's full name
	 * @param phoneNumber - the user's tel number
	 * @param company - the user's company
	 * @param email - the user's email
	 * @param note - a note for where this user was met
	 */
	public InsertOrEditContact(String fullName, String phoneNumber, String company, String email, String note) {
		super(fullName, phoneNumber, company, email, note);
	}

	@Override
	public Intent getSaveContactIntent() {
		Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
		intent.setType(Contacts.CONTENT_ITEM_TYPE);
		
		//Setting the name and surname
		intent.putExtra(Intents.Insert.NAME, fullName);
		//The phone number is already the same, so do nothing about that
		//Setting the company
		intent.putExtra(Intents.Insert.COMPANY, company);
		//Setting the email
		intent.putExtra(Intents.Insert.EMAIL_TYPE, CommonDataKinds.Email.TYPE_WORK);
		intent.putExtra(Intents.Insert.EMAIL, email);
		
		//Adding a note for which event they are at
		/*
		 * TODO find a way to append the notes
		 * These notes do not seem to be added when there is already a note for this contact
		 */
		//
		intent.putExtra(Intents.Insert.NOTES, note);
		
		//force return to this app when contact is saved
		intent.putExtra("finishActivityOnSaveCompleted", true);
		
		return intent;
	}

}
