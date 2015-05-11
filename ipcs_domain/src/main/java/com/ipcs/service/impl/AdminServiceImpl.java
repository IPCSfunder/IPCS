/**
 *
 */
package com.ipcs.service.impl;

import com.ipcs.dao.*;
import com.ipcs.model.*;
import com.ipcs.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Chao
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private PersonDao personDao;

    @Autowired
    private RelationshipTypeDao relationshipTypeDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MessageDao MessageDao;

    @Autowired
    private SchoolDao schoolDao;

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setSchoolDao(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    public void setMessageDao(com.ipcs.dao.MessageDao messageDao) {
        MessageDao = messageDao;
    }

    public void setRelationshipTypeDao(RelationshipTypeDao relationshipTypeDao) {
        this.relationshipTypeDao = relationshipTypeDao;
    }

    @Transactional
    public void addBatchSubodinates(List<Person> subodidates) {
        personDao.batchSave(subodidates);
    }

    @Transactional
    public void addPerson(Person person) {
        List<Role> roles = new ArrayList<Role>();
        roles.addAll(person.getRoles());
        List<School> schools = new ArrayList<School>();
        schools.addAll(person.getSchools());

        person.evictRoles();
        person.evictSchools();

        for (Role role : roles) {
            role = getRoleByName(role.getName());
            if (null != role)
                person.addRole(role);
        }

        for (School school : schools) {
            school = getSchoolByName(school.getName());
            if (null != school)
                person.addSchool(school);
        }

        List<Contact> contacts = new ArrayList<Contact>();
        contacts = person.getContacts();
        for (Contact contact : contacts) {
            System.out.print("Contact"+contact);
            RelationshipType type = getRelationshipTypeByName(contact.getRelationshipType().getName());
            contact.setRelationshipType(type);
        }

        personDao.save(person);
    }

    @Transactional
    public void removePerson(Person person) {
        personDao.delete(person);
    }

    @Transactional
    public List<Person> listAllPersonByRoleName(String schoolName, String roleName) {
        return personDao.find("select p from Person as p left join p.schools s left join p.roles as r where  s.name = '" + schoolName + "' and r.name = '" + roleName + "'");
    }


    @Transactional
    public void updatePerson(Person person) {
        Person persistPerson = personDao.load(person.getObjectId());
        persistPerson.getPersonDetail().setFirstName(person.getPersonDetail().getFirstName());
        persistPerson.getPersonDetail().setLastName(person.getPersonDetail().getLastName());
        persistPerson.getPersonDetail().setAge(person.getPersonDetail().getAge());
        persistPerson.getPersonDetail().setDateOfBirth(person.getPersonDetail().getDateOfBirth());
        persistPerson.getPersonDetail().setNationality(person.getPersonDetail().getNationality());
        persistPerson.getPersonDetail().setNric(person.getPersonDetail().getNric());



        List<Contact> persistContacts =  persistPerson.getContacts();
        for(Contact persistContact:persistContacts ){
            for(Contact transientContact: person.getContacts()){
                if(persistContact.getObjectId() == transientContact.getObjectId())
                {
                    persistContact.setMobileNumber(transientContact.getMobileNumber());
                    persistContact.setContacterName(transientContact.getContacterName());
                    persistContact.setAddress(transientContact.getAddress());
                    persistContact.getRelationshipType().setName(transientContact.getRelationshipType().getName());
                }
            }
        }

        personDao.update(persistPerson);
    }

    @Transactional
    public boolean broadcaseMessageTo(List<Person> subodidates) {
        return false;
    }


    @Transactional
    public void deleteBatchSubodinates(List<Person> subodinates) {
        personDao.deleteAll(subodinates);
    }

    @Transactional
    public Role getRoleByName(String name) {
        return roleDao.find("select r from Role as r where  r.name = '" + name + "'").get(0);
    }

    @Transactional
    public RelationshipType getRelationshipTypeByName(String name) {
        return relationshipTypeDao.find("select r from RelationshipType as r where  r.name = '" + name + "'").get(0);
    }


    @Transactional
    public School getSchoolByName(String name) {
        return schoolDao.find("select s from School as s where s.name = '" + name + "'").get(0);
    }

    @Transactional
    public List<School> getSchoolByType(String type) {
        return schoolDao.find("select s from School as s inner join s.type as t where  t.name = '" + type + "'");
    }

    @Transactional
    public Person getAdminInfo(String adminName) {
        return personDao.find("select p from Person as p left join fetch p.schools left join  p.roles left join p.contacts left join p.personDetail where p.account_name ='" + adminName + "'").get(0);
    }

    @Transactional
    public List<Message> listAllMessages(String adminName) {
        return MessageDao.find("select m from Message m inner join m.fromUser p where p.account_name = '" + adminName + "'");
    }

    @Transactional
    public List<Activity> listAllActivities(Long studentId) {
        return activityDao.find("from Activity m inner join fetch m.persons p where p.objectId = '" + studentId + "'");
    }

    @Transactional
    public List<Person> listAllChild(Long parentId) {
        return personDao.find("select w from Relationship r inner join r.whose w inner join r.type t inner join r.iswho i where t.name = 'PARENT' and i.objectId = '" + parentId + "'");
    }

    @Transactional
    public Person getChildDetail(String childName){
        return personDao.find("select p from Person as p left join fetch p.schools left join p.roles left join p.contacts left join fetch p.personDetail where p.account_name ='" + childName + "'").get(0);
    }

    @Transactional
    public List<Activity> listAllActivitiesFromAdmin(String adminName){
        return activityDao.find("select ac from Person as p inner join p.schools s inner join s.activities ac where p.account_name='" + adminName + "'");
    }

    @Transactional
    public Person findPersonByName(String accountName){
        return personDao.find("from Person p inner join fetch p.schools where p.account_name = '"+accountName+"'").get(0);
    }

    @Transactional
    public void addActivity(Activity activity){
        Person host = findPersonByName(activity.getHost().getAccount_name());
        activity.setHost(host);
        School school = getSchoolByName(activity.getSchool().getName());
        activity.setSchool(school);
        activityDao.save(activity);
    }


    @Transactional
    public Activity getActivityDetail(Long activityId){
        return activityDao.find("from Activity ac inner join fetch ac.host where ac.objectId = '"+activityId+"'").get(0);
    }
}
