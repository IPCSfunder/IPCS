package com.online_edu.model;

public class Person extends BasicObject{
	
	private String account_name;
	
	private String password_hash;
	
	public String getAccount_name() {
		return account_name;
	}
	
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	
	public String getPassword_hash() {
		return password_hash;
	}
	
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}
	
	public int hashCode(){
		return 31*account_name.hashCode();
	}
	
	public boolean equals(Object obj){
		if (null ==obj)
			return false;
		if(this == obj)
			return true;
		if(obj.getClass() != Person.class)
			return false;
		Person person = (Person)obj;
		return person.getAccount_name().equals(this.account_name);
	}
	
	public String toString(){
		return "Account name is "+ account_name +super.toString();
	}
	

}
