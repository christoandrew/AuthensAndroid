package com.iconasystems.android.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Alex on 2/21/2016.
 */
@ParseClassName("SharedLock")
public class SharedLock  extends ParseObject{
    private User otherParty;
    private String verificationToken;
    private String authenticationToken;
    private boolean verified;
    private Lock lock;

    public SharedLock() {
        setDefaultVerification();
    }


    public User getOwner() {
        return (User) get("owner");
    }

    public void setOwner(User owner) {
        put("owner", owner);
    }

    public User getOtherParty() {
        return (User) get("otherParty");
    }

    public void setOtherParty(User otherParty) {
        put("otherParty", otherParty);
    }

    public String getVerificationToken() {
        return getString("verificationToken");
    }

    public void setVerificationToken(String verificationToken) {
        put("verificationToken", verificationToken);
    }

    public String getAuthenticationToken() {
        return getString("authenticationToken");
    }

    public void setAuthenticationToken(String authenticationToken) {
        put("authenticationToken", authenticationToken);
    }

    public boolean isVerified() {
        return getBoolean("verified");
    }

    public void setVerified(boolean verified) {
        put("verified", verified);
    }

    public Lock getLock() {
        return (Lock) get("lock");
    }

    public void setLock(Lock lock) {
        put("lock", lock);
    }

    public void setDefaultVerification(){
        put("verified",false);
    }
}
