package com.ipcs.dao;

import com.ipcs.model.School;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chen Chao
 */
@Repository
public class SchoolDao extends GenericHibernateDao<School, Long> {

    public School findSchoolByName(String name) {
        List<School> schools = find("from School s where s.name = '" + name + "'");
        if (schools.size() == 0)
            return null;
        return schools.get(0);
    }

    public List<School> listSchoolsByType(String typeName){
        List<School> schools = find("select s from School s inner join s.type as t where  t.name = '" + typeName + "'");
        return schools;
    }

}
