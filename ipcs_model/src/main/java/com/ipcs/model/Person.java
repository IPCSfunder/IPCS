package com.ipcs.model;

/**
 * @author Chen Chao
 */

import com.ipcs.model.Base.BasicObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSON")
public class Person extends BasicObject {
    private Long objectId;

    private String account_name;

    private String password_hash;

    private List<Role> roles = new ArrayList<Role>();

    private List<Contact> contacts = new ArrayList<Contact>();

    private PersonDetail personDetail;

    private School school;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSON_OBJID", unique = true)
    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    @Column
    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    @Column
    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERSON_ROLE", joinColumns = {
            @JoinColumn(name = "PERSON_FK", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_FK")})
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_FK")
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }


    public void evictRoles() {
        this.roles.clear();
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SCHEDULE", joinColumns = {
            @JoinColumn(name = "PERSON_FK", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ACTIVITY_FK")})
    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_detail_fk")
    public PersonDetail getPersonDetail() {
        return personDetail;
    }

    public void setPersonDetail(PersonDetail personDetail) {
        this.personDetail = personDetail;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "whose", cascade = CascadeType.ALL)
    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
    }

    public void addRelationship(Relationship relationship) {
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
