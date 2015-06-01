package com.ipcs.dao;

import com.ipcs.model.Activity;
import com.ipcs.model.RelationshipType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chen Chao
 */
@Repository
public class RelationshipTypeDao extends GenericHibernateDao<RelationshipType, Long>{
    public RelationshipType findRelationshipTypeByName(String name) {
        List<RelationshipType> relationships = find("from RelationshipType as r where  r.name = '" + name + "'");
        if (relationships.size() == 0)
            return null;
        return relationships.get(0);
    }
}
