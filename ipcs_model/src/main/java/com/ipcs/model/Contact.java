package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Chen Chao
 *
 */

@Entity
@Table(name = "CONTACT")
public class Contact extends BasicObject {
	private Long objectId;

	private String address;

	private String postcode;

	private String mobileNumber;

	private boolean primary;

	private RelationshipType relationshipType;

	private String contacterName;

	private String emailAddress;

	private Person person;

	public Contact(ContactBuilder contactBuilder){
		this.address = contactBuilder.address;
		this.postcode = contactBuilder.postcode;
		this.mobileNumber = contactBuilder.mobileNumber;
		this.primary = contactBuilder.primary;
		this.relationshipType = contactBuilder.relationshipType;
		this.contacterName = contactBuilder.contacterName;
		this.emailAddress = contactBuilder.emailAddress;
		this.person = contactBuilder.person;
	}

	public static class ContactBuilder {
		private String address;
		private String postcode;
		private String mobileNumber;
		private boolean primary;
		private RelationshipType relationshipType;
		private String contacterName;
		private String emailAddress;
		private Person person;

		public ContactBuilder withAddress(String address){
            this.address = address;
            return this;
        }

        public ContactBuilder withPostcode(String postcode){
            this.postcode = postcode;
            return this;
        }

        public ContactBuilder withMobileNumber(String mobileNumber){
            this.mobileNumber = mobileNumber;
            return this;
        }

        public ContactBuilder withPrimary(Boolean primary){
            this.primary = primary;
            return this;
        }

        public ContactBuilder withRelationshipType(RelationshipType relationshipType){
            this.relationshipType = relationshipType;
            return this;
        }

        public ContactBuilder withContacterName(String contacterName){
            this.contacterName = contacterName;
            return this;
        }

		public ContactBuilder withEmailAddress(String emailAddress){
			this.emailAddress = emailAddress;
			return this;
		}

		public ContactBuilder withPerson(Person person){
			this.person = person;
			return this;
		}

		public Contact builder() {
			return new Contact(this);
		}
	}

	public Contact(){
		super();
	}

	public Contact(String address, String mobileNumber, String emailAddress) {
		super();
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.emailAddress = emailAddress;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTACT_OBJID", unique = true, nullable = false)
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@Column(name="POSTCODE")
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_FK")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RELATIONSHIP_TYPE_FK")
	public RelationshipType getRelationshipType() {
		return relationshipType;
	}



	public void setRelationshipType(RelationshipType relationshipType) {
		this.relationshipType = relationshipType;
	}

	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="EMAIL_ADDRESS")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name="PRIMARY_CONTACT")
	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}


	@Column(name="MOBILE_NUMBER")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name="CONTACT_NAME")
	public String getContacterName() {
		return contacterName;
	}

	public void setContacterName(String contacterName) {
		this.contacterName = contacterName;
	}

	public int hashCode(){
		final int facter = 31;
		int result =1;
		result = result*17+mobileNumber.hashCode();
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
		return (contact.getMobileNumber().equals(this.mobileNumber));
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
