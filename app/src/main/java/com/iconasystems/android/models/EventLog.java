package com.iconasystems.android.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.sql.Time;
import java.sql.Date;

/**
 * Created by Alex on 2/16/2016.
 */
@ParseClassName("EventLog")
public class EventLog extends ParseObject {
    private User user;
    private Date date;
    private Time time;
    private Lock lock;

    public EventLog() {
    }

    public EventLog(User user, Date date, Time time, Lock lock) {
        super();
        this.user = user;
        this.date = date;
        this.time = time;
        this.lock = lock;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
