package com.iconasystems.android.authensandroid;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iconasystems.android.models.Lock;
import com.iconasystems.android.models.SharedLock;
import com.iconasystems.android.models.User;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeySharingFragment extends Fragment {
    private Lock lock;
    private User user;

    public KeySharingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_key_sharing, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getActivity().getIntent().getExtras();
        String lock_id = bundle.getString("lock_id");
        ParseQuery<Lock> getLock = ParseQuery.getQuery("Lock");

        getLock.whereEqualTo("doorId", lock_id);

        getLock.findInBackground(new FindCallback<Lock>() {
            @Override
            public void done(List<Lock> objects, ParseException e) {
                if (e == null) {
                    Lock lock = objects.get(0);
                    lock.fetchInBackground(new GetCallback<Lock>() {
                        @Override
                        public void done(final Lock lock, ParseException e) {
                            if (e == null){
                                String current_user = User.getCurrentUser().getUsername();
                                ParseQuery<User> users = new ParseQuery<User>(User.class);
                                users.whereNotEqualTo("username", current_user);
                                users.findInBackground(new FindCallback<User>() {
                                    @Override
                                    public void done(List<User> objects, ParseException e) {
                                        for (int i = 0; i < objects.size(); i++) {
                                            User user = objects.get(i);
                                            Log.d("User", user.getUsername());
                                            SharedLock sharedLock = new SharedLock();
                                            sharedLock.setLock(lock);
                                            sharedLock.setOtherParty(user);
                                            sharedLock.setVerificationToken("110202");
                                            sharedLock.saveEventually();
                                        }
                                    }
                                });
                            }
                        }
                    });

                } else {
                    e.printStackTrace();
                }
            }
        });



    }

}
