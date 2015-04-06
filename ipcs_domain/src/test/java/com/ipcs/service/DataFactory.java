/**
 * 
 */
package com.ipcs.service;

import java.util.ArrayList;
import java.util.List;

import com.ipcs.model.Contact;
import com.ipcs.model.Permission;
import com.ipcs.model.Person;
import com.ipcs.model.PersonDetail;
import com.ipcs.model.Role;
import com.ipcs.model.School;
import com.ipcs.model.SchoolType;

/**
 * @author Chen Chao
 *
 */
public class DataFactory {

    public static Person preparePerson(String name, String password) {
	Person person = new Person();
	person.setAccount_name(name);
	person.setPassword_hash(password);
	return person;
    }

    public static List<Person> prepareStudent(int number, String name, String password) {
	List<Person> students = new ArrayList<Person>();
	for (int t = 0; t < number; t++) {
	    Person person = new Person();
	    person.setAccount_name(name+t);
	    person.setPassword_hash(password);
	    students.add(person);
	}
	return students;
    }

 

    public PersonDetail preparePersonDetail() {
	return null;
    }

    public Contact prepareContact() {
	return null;
    }
    

}
