/**
 *
 */
package com.ipcs.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ipcs.dao.*;
import com.ipcs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipcs.service.AdminService;

/**
 * @author Chen Chao
 */

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private PersonDao personDao;

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

    @Transactional
    public void addBatchSubodinates(List<Person> subodidates) {
        personDao.batchSave(subodidates);
    }

    @Transactional
    public void addPerson(Person person) {
        Set<Role> roles = new HashSet<Role>();
        roles.addAll(person.getRoles());
        Set<School> schools =  new HashSet<School>();
        schools.addAll(person.getSchools());

        person.evictRoles();
        person.evictSchools();

        for(Role role: roles){
            role =getRoleByName(role.getName());
            if(null != role)
                person.addRole(role);
        }

        for(School school: schools){
            school =getSchoolByName(school.getName());
            if(null != school)
                person.addSchool(school);
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
        personDao.update(person);
    }

    @Transactional
    public boolean broadcaseMessageTo(List<Person> subodidates) {
        // TODO Auto-generated method stub
        return false;
    }


    @Transactional
    public void deleteBatchSubodinates(List<Person> subodinates) {
        personDao.deleteAll(subodinates);
    }

    @Transactional
    public Role getRoleByName(String name) {
        System.out.print(name);
        return roleDao.find("select r from Role as r where  r.name = '" + name + "'").get(0);
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
        return personDao.find("from Person as p left join fetch p.schools left join fetch p.roles left join fetch p.contacts left join fetch p.personDetail where p.account_name ='" + adminName + "'").get(0);
    }

    @Transactional
    public List<Message> listAllMessages(String adminName){
        return MessageDao.find("select s from Message m inner join m.fromUser p where p.account_name = '" + adminName + "'");
    }

    @Transactional
    public List<Activity> listAllActivities(Long studentId){
        return activityDao.find("from Activity m inner join fetch m.persons p where p.objectId = '" + studentId + "'");
    }

    @Transactional
    public List<Person> listAllChild(Long parentId){
        return activityDao.find("from Activity m inner join fetch m.persons p where p.objectId = '" + studentId + "'");
    }

}
