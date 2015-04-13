package com.ipcs.model;

/**
 * @author Chen Chao
 */

import java.util.HashSet;
import java.util.Set;

public class Person extends BasicObject {

    private String account_name;

    private String password_hash;

    private Set<Role> roles = new HashSet<Role>();

    private Contact contact;

    private PersonDetail personDetail;

    private Set<School> schools = new HashSet<School>();

    private Set<Message> messages = new HashSet<Message>();



    public Person(String name, String password) {
        super();
        this.account_name = name;
        this.password_hash = password;
    }

    public Person() {
        super();
        roles.add(new Role());
        schools.add(new School());
    }


    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }



    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<School> getSchools() {
        return schools;
    }

    public void setSchools(Set<School> schools) {
        this.schools = schools;
    }

    public void addSchool(School school) {
        this.schools.add(school);
    }

    public void evictSchools(){
        this.schools.clear();
    }


    public void evictRoles(){
        this.roles.clear();
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }



    public void addMessage(Message message){
        this.messages.add(message);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public PersonDetail getPersonDetail() {
        return personDetail;
    }

    public void setPersonDetail(PersonDetail personDetail) {
        this.personDetail = personDetail;
    }

    public int hashCode() {
        return 31 * 17 + account_name.hashCode();
    }

    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (this == obj)
            return true;
        if (obj.getClass() != Person.class)
            return false;
        Person person = (Person) obj;
        return person.getAccount_name().equals(this.account_name);
    }

    public String toString() {
        return "Account name is " + account_name + super.toString();
    }


}
