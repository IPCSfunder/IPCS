package com.ipcs.model;

import java.util.Date;

/**
 * @author Chen Chao
 *
 */
public class PersonDetail extends BasicObject {

	private String last_name;

	private String fist_name;

	private Integer age;

	private Sex sex;

	private String nationality;

	private Date date_of_birth;

	private String nric;

	private String nick_name;

	private Boolean market_option;
	
	private Person person;
	
	public PersonDetail(PersonBuilder builder){

		this.last_name = builder.last_name;
		this.fist_name = builder.first_name;
		this.age = builder.age;
		this.sex = builder.sex;
		this.nationality = builder.nationality;
		this.date_of_birth = builder.date_of_birth;
		this.nric = builder.nric;
		this.nick_name = builder.nick_name;
		this.market_option = builder.market_option;
	}
	
	public static enum Sex{
		MALE,FEMALE
	}
	
	public static class PersonBuilder{
		private String last_name;
		private String first_name;
		private Integer age;
		private Sex sex;
		private String nationality;
		private Date date_of_birth;
		private String nric;
		private String nick_name;
		private Boolean market_option = false;
		
		public PersonBuilder withNickName(String nick_name){
			this.nick_name = nick_name;
			return this;
		}
		
		
		public PersonBuilder withLastName(String last_name){
			this.last_name = last_name;
			return this;
		}
		
		public PersonBuilder withFirstName(String first_name){
			this.first_name = first_name;
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
		
		public PersonBuilder withDob(Date date_of_birth){
			this.date_of_birth = date_of_birth;
			return this;
		}
		
		public PersonBuilder withNric(String nric){
			this.nric = nric;
			return this;
		}
		
		public PersonBuilder withMarketOption(Boolean market_option){
			this.market_option = market_option;
			return this;
		}
		
		
		
		public PersonDetail build(){
			return new PersonDetail(this);
		}
	}
	
	
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFist_name() {
		return fist_name;
	}

	public void setFist_name(String fist_name) {
		this.fist_name = fist_name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Boolean getMarket_option() {
		return market_option;
	}

	public void setMarket_option(Boolean market_option) {
		this.market_option = market_option;
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
		details.append("First name is ").append(fist_name);
		details.append("last name is ").append(last_name);
		details.append(super.toString());
		return details.toString();
	}

}
