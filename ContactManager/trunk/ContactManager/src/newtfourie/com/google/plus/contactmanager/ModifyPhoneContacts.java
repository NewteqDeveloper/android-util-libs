package newtfourie.com.google.plus.contactmanager;

import android.content.Intent;

public abstract class ModifyPhoneContacts {

	protected String fullName, phoneNumber, company, email, note;
	
	/**
	 * The constructor which will set the provided information
	 * @param fullName - the user's full name
	 * @param phoneNumber - the user's tel number
	 * @param company - the user's company
	 * @param email - the user's email
	 * @param note - a note for where this user was met
	 */
	public ModifyPhoneContacts(String fullName, String phoneNumber, String company, String email, String note)
	{
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.company = company;
		this.email = email;
		this.note = note;
	}
	
	/**
	 * This method will save the contact to the device
	 * @return true if the contact was saved successfully
	 */
	public abstract Intent getSaveContactIntent();
}
