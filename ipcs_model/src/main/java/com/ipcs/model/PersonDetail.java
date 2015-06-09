package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Chen Chao
 *
 */

@Entity
@Table(name = "PERSON_DETAIL")
public class PersonDetail extends BasicObject {
	private Long objectId;

	private String firstName;

	private String lastName;

	private Integer age;

	private String sex;

	private String nationality;

	private Date dateOfBirth;

	private String nric;

	private String nickName;

	private Boolean marketOption;
	
	private Person person;

	private String address;

	private String postcode;

	public PersonDetail(){
		super();
	}
	
	public PersonDetail(PersonBuilder builder){

		this.lastName = builder.lastName;
		this.firstName = builder.firstName;
		this.age = builder.age;
		this.sex = builder.sex;
		this.nationality = builder.nationality;
		this.dateOfBirth = builder.dateOfBirth;
		this.nric = builder.nric;
		this.nickName = builder.nickName;
		this.marketOption = builder.marketOption;
		this.postcode = builder.postcode;
		this.address = builder.address;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERSON_DETAIL_OBJID", unique = true, nullable = false)
	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@Column
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DOB")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="MARKET_OPTION")
	public Boolean isMarketOption() {
		return marketOption;
	}

	public void setMarketOption(Boolean marketOption) {
		this.marketOption = marketOption;
	}

	@Column(name="NATIONALITY")
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name="NICK_NAME")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name="NRIC")
	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	@OneToOne(mappedBy = "personDetail")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name="SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="POSTCODE")
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public static class PersonBuilder{
		private String lastName;
		private String firstName;
		private Integer age;
		private String sex;
		private String nationality;
		private Date dateOfBirth;
		private String nric;
		private String nickName;
		private Boolean marketOption = false;
		private String address;
		private String postcode;
		
		public PersonBuilder withNickName(String nickName){
			this.nickName = nickName;
			return this;
		}
		
		
		public PersonBuilder withLastName(String lastName){
			this.lastName = lastName;
			return this;
		}
		
		public PersonBuilder withFirstName(String firstName){
			this.firstName = firstName;
			return this;
		}
		
		public PersonBuilder withAge(Integer age){
			this.age = age;
			return this;
		}
		
		public PersonBuilder withSex(String sex){
			this.sex = sex;
			return this;
		}
		
		public PersonBuilder withNationality(String nationality){
			this.nationality = nationality;
			return this;
		}
		
		public PersonBuilder withDob(Date dateOfBirth){
			this.dateOfBirth = dateOfBirth;
			return this;
		}
		
		public PersonBuilder withNric(String nric){
			this.nric = nric;
			return this;
		}
		
		public PersonBuilder withMarketOption(Boolean marketOption){
			this.marketOption = marketOption;
			return this;
		}

		public PersonBuilder withAddress(String address){
			this.address = address;
			return this;
		}

		public PersonBuilder withPostcode(String postcode){
			this.postcode = postcode;
			return this;
		}
		
		
		
		public PersonDetail build(){
			return new PersonDetail(this);
		}
	}



	public int hashCode() {
		final int facter = 31;
		int result = 1;;
		result = result * 17 + nric.hashCode();
		return result;
	}

	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		if (obj.getClass() != Person.class)
			return false;
		PersonDetail personDetails = (PersonDetail) obj;
		return (personDetails.getNric().equals(this.getNric()));
	}

	public String toString() {
		StringBuilder details = new StringBuilder();
		details.append("First name is ").append(firstName);
		details.append("last name is ").append(lastName);
		details.append(super.toString());
		return details.toString();
	}

}
