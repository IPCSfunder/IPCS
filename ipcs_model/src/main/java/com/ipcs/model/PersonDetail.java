package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import java.util.Date;

/**
 * @author Chen Chao
 *
 */
public class PersonDetail extends BasicObject {

	private String firstName;

	private String lastName;

	private Integer age;

	private Sex sex;

	private String nationality;

	private Date dateOfBirth;

	private String nric;

	private String nickName;

	private Boolean marketOption;
	
	private Person person;

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
	}
	
	public static enum Sex{
		MALE,FEMALE
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Boolean isMarketOption() {
		return marketOption;
	}

	public void setMarketOption(Boolean marketOption) {
		this.marketOption = marketOption;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public static class PersonBuilder{
		private String lastName;
		private String firstName;
		private Integer age;
		private Sex sex;
		private String nationality;
		private Date dateOfBirth;
		private String nric;
		private String nickName;
		private Boolean marketOption = false;
		
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
		
		public PersonBuilder withSex(Sex sex){
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
