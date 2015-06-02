package com.ipcs.model;

import com.ipcs.model.Base.BasicObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Chen Chao
 */

@Entity
@Table(name = "ACTIVITY")
public class Activity extends BasicObject {
    private Long objectId;

    private String name;

    private String location;

    private String description;

    private Date startTime;

    private Date endTime;

    private Person host;

    private School school;

    private ActivityType activityType;

    private List<Person> persons = new ArrayList<Person>();

    public Activity() {
        super();
    }

    public Activity(ActivityBuilder activityBuilder) {
        this.name = activityBuilder.name;
        this.location = activityBuilder.location;
        this.description = activityBuilder.description;
        this.startTime = activityBuilder.startTime;
        this.endTime = activityBuilder.endTime;
        this.host = activityBuilder.host;
        this.school = activityBuilder.school;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITY_OBJID", unique = true, nullable = false)
    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOST")
    public Person getHost() {
        return host;
    }

    public void setHost(Person host) {
        this.host = host;
    }

    @Column(name = "LOCATION")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SCHEDULE", joinColumns = {
            @JoinColumn(name = "ACTIVITY_FK", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "PERSON_FK")})
    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHOOL_FK")
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVITY_TYPE_FK")
    public ActivityType getActivityType() {
        return activityType;
    }


    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public int hashCode() {
        int factor = 31;
        int result = 17 * factor + name.hashCode();
        result = 17 * result + location.hashCode();
        result = 17 * result + startTime.hashCode();
        result = 17 * result + endTime.hashCode();
        return result;
    }

    public boolean equals(Object obj) {
        if (null == obj)
            return false;
        if (this == obj)
            return true;
        if (obj.getClass() != Permission.class)
            return false;
        Activity activity = (Activity) obj;
        return this.name.equals(activity.getName()) && this.activityType.equals(activity.getActivityType()) &&this.location.equals(activity.getLocation()) && this.startTime.equals((activity.getStartTime()));

    }

    public String toString() {
        return "Activity name is " + name + " and location is " + location + super.toString();
    }

    public static class ActivityBuilder {
        private String name;
        private String location;
        private String description;
        private Date startTime;
        private Date endTime;
        private Person host;
        private School school;

        public ActivityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ActivityBuilder withLocation(String location) {
            this.location = location;
            return this;
        }

        public ActivityBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ActivityBuilder withStartTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public ActivityBuilder withEndTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        public ActivityBuilder withHost(Person host) {
            this.host = host;
            return this;
        }

        public ActivityBuilder withSchool(School school) {
            this.school = school;
            return this;
        }

        public Activity builder() {
            return new Activity(this);
        }

    }
}
