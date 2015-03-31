package com.ipcs.model;

/**
 * @author Chen Chao
 *
 */
public class Contact extends BasicObject{
	
	private String address;
	
	private String mobile_number;
	
	private String email_address;
	
	private Person person;
	
	
	public Contact(String address, String mobile_number, String email_address) {
		super();
		this.address = address;
		this.mobile_number = mobile_number;
		this.email_address = email_address;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int hashCode(){
		final int facter = 31;
		int result =1;
		result = facter*17+address.hashCode();
		result = result*17+mobile_number.hashCode();
		result = result*17+email_address.hashCode();
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
				&&(contact.getEmail_address().equals(this.email_address))
				&&(contact.getMobile_number().equals(this.mobile_number));
	}
	
	public String toString(){
		StringBuilder details = new StringBuilder();
		details.append("Contact address is ").append(address);
		details.append("mobile number is ").append(mobile_number);
		details.append("email address is ").append(email_address);
		details.append(super.toString());
		return details.toString();
	}

}
