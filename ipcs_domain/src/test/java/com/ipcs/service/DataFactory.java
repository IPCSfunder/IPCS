/**
 *
 */
package com.ipcs.service;

import com.ipcs.model.Contact;
import com.ipcs.model.Person;
import com.ipcs.model.PersonDetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Chen Chao
 */
public class DataFactory {

    public static Person preparePerson(String name, String password) {
        Person person = new Person();
        person.setAccount_name(name);
        person.setPassword_hash(password);
        person.setPersonDetail(preparePersonDetail());
        return person;
    }

    public static List<Person> prepareStudent(int number, String name, String password) {
        List<Person> students = new ArrayList<Person>();
        for (int t = 0; t < number; t++) {
            Person person = new Person();
            person.setAccount_name(name + t);
            person.setPassword_hash(password);
            students.add(person);
        }
        return students;
    }


    public static PersonDetail preparePersonDetail() {
        return new PersonDetail.PersonBuilder().withAge(21).withDob(new Date()).withFirstName("DetailFirst").withLastName("DetailLast").build();
    }

    public Contact prepareContact() {
        return null;
    }


}
