package com.iconasystems.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * Created by Alex on 2/15/2016.
 */

@ParseClassName("_User")

public class User extends ParseUser implements Parcelable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private ParseFile profilePicture;
    private String username;
    private String email;
    private String password;


    public User() {
        super();
    }

    public User(Parcel parcel) {
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
        this.phoneNumber = parcel.readString();
        this.username = parcel.readString();
        this.email = parcel.readString();
        this.password = parcel.readString();
    }

    public String getEmail() {
        return getString("email");
    }

    public void setEmail(String email) {
        put("email", email);
    }

    public void setPassword(String password) {
        put("password", password);
    }

    public String getFirstName() {
        return getString("firstname");
    }

    public void setFirstName(String firstName) {
        put("firstname", firstName);
    }

    public String getUsername() {
        return getString("username");
    }

    public void setUsername(String username) {
        put("username", username);
    }

    public String getLastName() {
        return getString("lastname");
    }

    public void setLastName(String lastName) {
        put("lastname", lastName);
    }

    public String getPhoneNumber() {
        return getString("phone");
    }

    public void setPhoneNumber(String phoneNumber) {
        put("phone", phoneNumber);
    }

    public ParseFile getProfilePicture() {
        return getParseFile("profile_pic");
    }

    public void setProfilePicture(ParseFile profilePicture) {
        put("profile_pic", profilePicture);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(phoneNumber);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeString(password);
        //parcel.writeP(profilePicture);
    }

    final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel parcel) {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int i) {
            return new User[i];
        }
    };
}
