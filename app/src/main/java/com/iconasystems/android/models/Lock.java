package com.iconasystems.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.UUID;

/**
 * Created by Alex on 2/15/2016.
 */
@ParseClassName("Lock")

public class Lock extends ParseObject implements Parcelable {
    private String simNumber;
    private User owner;
    private String authToken;
    private String state;
    private String id;
    private String doorName;
    public Lock() {
    }

    public Lock(Parcel parcel){
        this.doorName = parcel.readString();
        this.authToken = parcel.readString();
        this.simNumber = parcel.readString();
        this.state = parcel.readString();
        this.id = parcel.readString();
        this.owner = parcel.readParcelable(User.class.getClassLoader());
    }

    public String getSimNumber() {
        return getString("simNumber");
    }

    public void setSimNumber(String simNumber) {
        put("simNumber", simNumber);
    }

    public User getOwner() {
        return (User) get("owner");
    }

    public void setOwner(User owner) {
        put("owner", owner);
    }

    public String getAuthToken() {
        return getString("authToken");
    }

    public void setAuthToken() {
        put("authToken", UUID.randomUUID().toString());
    }



    public String getState() {
        return  getString("state");
    }

    public void setState(String state) {
        put("state", state.toString());
    }

    public String getId() {
        return getString("doorId");
    }

    public void setId() {
        put("doorId", UUID.randomUUID().toString());
    }

    public void setDefaultState(){
        setState("CLOSED");
    }

    public String getDoorName() {
        return getString("doorName");
    }

    public void setDoorName(String doorName) {
        put("doorName",doorName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(doorName);
        parcel.writeString(authToken);
        parcel.writeString(simNumber);
        parcel.writeString(state);
        parcel.writeString(id);
        parcel.writeParcelable(owner, i);

    }

    final Creator<Lock> CREATOR = new Creator<Lock>() {
        @Override
        public Lock createFromParcel(Parcel parcel) {
            return new Lock(parcel);
        }

        @Override
        public Lock[] newArray(int i) {
            return new Lock[i];
        }
    };

}
