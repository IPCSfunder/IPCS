package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Chen Chao
 */
public class UserGroup extends BasicObject {

    private String name;

    private Person orginizer;

    private Person groupMember;

    public UserGroup() {
    }

    public Person getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(Person groupMember) {
        this.groupMember = groupMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOrginizer() {
        return orginizer;
    }

    public void setOrginizer(Person orginizer) {
        this.orginizer = orginizer;
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
