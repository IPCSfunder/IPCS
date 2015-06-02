package com.ipcs.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ipcs.model.Person;

import java.util.List;

/**
 * @author Chen Chao
 *
 */

@Repository
public class PersonDao extends GenericHibernateDao<Person, Long> {

    /**
     * Person and school info
     * @param name
     * @return
     */
    public Person findPersonByName(String name){
        List<Person> persons = find("select p from Person p inner join fetch p.school where p.account_name = '"+name+"'");
        if(persons.size() == 0)
            return null;
        return persons.get(0);
    }

    /**
     * Person and all details are fetched
     * @param name
     * @return
     */
    public Person findPersonDetailsByName(String name){
        List<Person> persons = find("select p from Person p left join fetch p.school left join fetch p.roles left join p.contacts left join fetch p.personDetail where p.account_name ='" + name + "'");
        if(persons.size() == 0)
            return null;
        return persons.get(0);
    }

    public List<Person> listPersonsBy(String schoolName, String roleName) {
        return find("select p from Person p inner join p.school s inner join p.roles as r where  s.name = '" + schoolName + "' and r.name = '" + roleName + "'");
    }
}
