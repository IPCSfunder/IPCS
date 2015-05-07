package com.ipcs.message;

import java.util.List;

/**
 * Created by Shixuan on 2015/5/6.
 */
public class ActivityMsg {

    private long id;
    private int length;
    private List Activities;

    public ActivityMsg(long id, int length, List activities) {
        this.id = id;
        this.length = length;
        Activities = activities;
    }

    public long getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public List getActivities() {
        return Activities;
    }
}
