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
public class DummyDataFactory {
    
    public List<Person> getAdminInstance(){
	DummyDataFactory dummy = new DummyDataFactory();
	Role adminRole = dummy.prepareRole("Admin");
	Permission teacherPermission = dummy.perparePermission("Add_Teacher");
	Permission studentPermission = dummy.perparePermission("Add_Child");
	adminRole.addPermission(studentPermission);
	adminRole.addPermission(teacherPermission);
	Role studentRole = dummy.prepareRole("Student");
	Person admin = dummy.prepareAdmin();
	List<Person> adminAndStudents = dummy.prepareStudent(3);
	School school = dummy.prepareSchool();
	SchoolType schoolType = dummy.prepareSchooType();
	school.setType(schoolType);
	for(Person student: adminAndStudents){
	    student.addRole(studentRole);
	    student.addSchool(school);
	}
	admin.addRole(adminRole);	
	admin.addSchool(school);
	adminAndStudents.add(admin);
	return adminAndStudents;
    }
    
    

    public Person prepareAdmin() {
	Person person = new Person();
	person.setAccount_name("DummyAdmin");
	person.setPassword_hash("Test");
	return person;
    }

    public List<Person> prepareStudent(int number) {
	List<Person> students = new ArrayList<Person>();
	for (int t = 0; t < number; t++) {
	    Person person = new Person();
	    person.setAccount_name("Student"+t);
	    person.setPassword_hash("Test");
	    students.add(person);
	}
	return students;
    }

    public Role prepareRole(String roleName) {
	Role role = new Role();
	role.setName(roleName);
	return role;
    }

    public Permission perparePermission(String permissionName) {
	Permission permission = new Permission();
	permission.setName(permissionName);
	return permission;
    }

    public School prepareSchool() {
	School school = new School();
	school.setName("Pungol Primary School");
	return school;
    }

    public PersonDetail preparePersonDetail() {
	return null;
    }

    public Contact prepareContact() {
	return null;
    }
    
    public SchoolType prepareSchooType(){
	return new SchoolType("Primary School");
    }

}
