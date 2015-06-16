package com.ipcs.message;

import java.util.Date;

/**
 * Created by Shixuan on 2015/5/7.
 */
public class ActivityBean {
    private String name;

    private String location;

    private String description;

//    private Date startTime;

    private String startTime;

    private String host_account;

    public ActivityBean(String name, String location, String description, String startTime, String host_account) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.startTime = startTime;
        this.host_account = host_account;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getHost_account() {
        return host_account;
    }
}
