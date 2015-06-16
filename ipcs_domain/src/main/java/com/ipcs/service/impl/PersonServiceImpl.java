/**
 *
 */
package com.ipcs.service.impl;

import com.ipcs.dao.*;
import com.ipcs.model.*;
import com.ipcs.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Chao
 */

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private PersonDetailDao personDetailDao;

    @Autowired
    private RelationshipTypeDao relationshipTypeDao;

    @Transactional
    public void addBatchSubodinates(List<Person> subodidates) {
        personDao.batchSave(subodidates);
    }

    @Transactional
    public void addPerson(Person person) {
        if (null != person.getSchool())
            person.setSchool(schoolDao.findSchoolByName(person.getSchool().getName()));

        List<Role> roles = new ArrayList<Role>();
        roles.addAll(person.getRoles());
        person.evictRoles();
        for (Role role : roles) {
            role = roleDao.findRoleByName(role.getName());
            if (null != role)
                person.addRole(role);
        }

        List<Contact> contacts = new ArrayList<Contact>();
        for (Contact contact : person.getContacts()) {
            if (contact.getContacterName() != null && contact.getContacterName().equals(""))
            {
                RelationshipType type = relationshipTypeDao.findRelationshipTypeByName(contact.getRelationshipType().getName());
                contact.setRelationshipType(type);
                contact.setPerson(person);
                contacts.add(contact);
            }
        }
        person.setContacts(contacts);

        List<Activity> activities = new ArrayList<Activity>();
        if(person.getActivities()!=null){
            for (Activity activity : person.getActivities()) {
                Activity persistedActivity = activityDao.findActivityDetailsById(activity.getObjectId());
                activities.add(persistedActivity);
            }
            person.setActivities(activities);
        }
        if (person.getPersonDetail() != null)
            personDetailDao.save(person.getPersonDetail());
        personDao.save(person);
    }

    @Transactional(readOnly = true)
    public List<Person> listPersonsByRoleName(String schoolName, String roleName) {
        return personDao.listPersonsBy(schoolName, roleName);
    }


    @Transactional
    public void updatePerson(Person person) {
        Person persistPerson = personDao.load(person.getObjectId());
        persistPerson.setAccount_name(person.getAccount_name());
        persistPerson.setPassword_hash(person.getPassword_hash());
        PersonDetail personDetail = persistPerson.getPersonDetail();
        personDetail.setFirstName(person.getPersonDetail().getFirstName());
        personDetail.setLastName(person.getPersonDetail().getLastName());
        personDetail.setAge(person.getPersonDetail().getAge());
        personDetail.setDateOfBirth(person.getPersonDetail().getDateOfBirth());
        personDetail.setNationality(person.getPersonDetail().getNationality());
        personDetail.setNric(person.getPersonDetail().getNric());
        personDetailDao.update(personDetail);


        List<Contact> persistContacts = persistPerson.getContacts();
        for (Contact persistContact : persistContacts) {
            for (Contact transientContact : person.getContacts()) {
                if (persistContact.getObjectId() == transientContact.getObjectId()) {
                    persistContact.setMobileNumber(transientContact.getMobileNumber());
                    persistContact.setContacterName(transientContact.getContacterName());
                    persistContact.setAddress(transientContact.getAddress());
                    persistContact.setRelationshipType(relationshipTypeDao.findRelationshipTypeByName(transientContact.getRelationshipType().getName()));
                }
            }
        }
        persistPerson.setPersonDetail(personDetail);

        List<Activity> activities = new ArrayList<Activity>();
        if(person.getActivities()!=null){
            for (Activity activity : person.getActivities()) {
                Activity persistedActivity = activityDao.findActivityDetailsById(activity.getObjectId());
                activities.add(persistedActivity);
            }
            persistPerson.setActivities(activities);
        }
        personDao.update(persistPerson);
    }


    @Transactional
    public void deleteBatchSubodinates(List<Person> subodinates) {
        personDao.deleteAll(subodinates);
    }

    @Transactional(readOnly = true)
    public Person getPersonDetail(String name) {
        return personDao.findPersonDetailsByName(name);
    }

    @Transactional(readOnly = true)
    public List<Person> listChildsByParentId(Long parentId) {
        return personDao.find("select w from Relationship r inner join r.whose w inner join r.type t inner join r.iswho i where t.name = 'PARENT' and i.objectId = '" + parentId + "'");
    }

    @Transactional(readOnly = true)
    public Person findPersonByName(String accountName) {
        return personDao.findPersonByName(accountName);
    }


    @Transactional
    public void removePerson(Long personId) {
        Person person = personDao.get(personId);
        personDao.delete(person);
    }

    @Transactional
    public List<RelationshipType> listRelationshipTypes() {
        return relationshipTypeDao.findAll();
    }
}
