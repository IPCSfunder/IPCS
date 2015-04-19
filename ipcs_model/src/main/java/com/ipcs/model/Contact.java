package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

/**
 * @author Chen Chao
 *
 */
public class Contact extends BasicObject {
	
	private String address;

	private String postcode;
	
	private String mobileNumber;

	private boolean primary;

	private RelationshipType relationshipType;

	private String contacterName;
	
	private String emailAddress;
	
	private Person person;

	public Contact(){
		super();
	}

	public Contact(String address, String mobileNumber, String emailAddress) {
		super();
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public RelationshipType getRelationshipType() {
		return relationshipType;
	}



	public void setRelationshipType(RelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getContacterName() {
		return contacterName;
	}

	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}

	public int hashCode(){
		final int facter = 31;
		int result =1;
		result = facter*17+address.hashCode();
		result = result*17+mobileNumber.hashCode();
		result = result*17+emailAddress.hashCode();
		return result;
	}
	
	public boolean equals(Object obj){
		if (null ==obj)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != Person.class)
			return false;
		Contact contact = (Contact)obj;
		return (contact.getAddress().equals(this.address))
				&&(contact.getEmailAddress().equals(this.emailAddress))
				&&(contact.getMobileNumber().equals(this.mobileNumber));
	}
	
	public String toString(){
		StringBuilder details = new StringBuilder();
		details.append("Contact address is ").append(address);
		details.append("mobile number is ").append(mobileNumber);
		details.append("email address is ").append(emailAddress);
		details.append(super.toString());
		return details.toString();
	}

}
