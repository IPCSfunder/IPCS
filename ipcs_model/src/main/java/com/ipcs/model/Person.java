package com.ipcs.model;

/**
 * @author Chen Chao
 */

import com.ipcs.model.Base.BasicObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person extends BasicObject {
    private String account_name;

    private String password_hash;

    private List<Role> roles = new ArrayList<Role>();

    private List<Contact> contacts = new ArrayList<Contact>();

    private PersonDetail personDetail;

    private List<School> schools = new ArrayList<School>();

    private List<Relationship> relationships = new ArrayList<Relationship>();

    private List<Activity> activities = new ArrayList<Activity>();

    public Person(String name, String password) {
        super();
        this.account_name = name;
        this.password_hash = password;
    }

    public Person(String name) {
        super();
        this.account_name = name;
    }

    public Person() {
        super();
    }


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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
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

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }



    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact){
        this.contacts.add(contact);
    }

    public PersonDetail getPersonDetail() {
        return personDetail;
    }

    public void setPersonDetail(PersonDetail personDetail) {
        this.personDetail = personDetail;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    public void addRelationship(Relationship relationship){
        this.relationships.add(relationship);
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
