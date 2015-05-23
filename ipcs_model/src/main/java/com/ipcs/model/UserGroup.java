package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chen Chao
 */

@Entity
@Table(name = "USER_GROUP")
public class UserGroup extends BasicObject {
    private Long objectId;

    private String name;

    private Person organizer;

    private Person groupMember;

    public UserGroup() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_GROUP_OBJID", unique = true)
    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_TYPE_FK")
    public Person getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(Person groupMember) {
        this.groupMember = groupMember;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUOP_MEMBER")
    public Person getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Person organizer) {
        this.organizer = organizer;
    }



    public int hashCode() {
        int factor = 31;
        int result = 17 * factor + name.hashCode();
        return result;
    }

    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (this == obj)
            return true;
        if (obj.getClass() != Permission.class)
            return false;
        UserGroup activity = (UserGroup) obj;
        return this.name.equals(activity.getName());
    }

    public String toString() {
        return "User group name is " + name  + super.toString();
    }
}
