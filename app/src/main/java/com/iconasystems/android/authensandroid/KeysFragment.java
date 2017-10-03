package com.iconasystems.android.authensandroid;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.iconasystems.android.models.Lock;
import com.iconasystems.android.models.User;
import com.iconasystems.android.utils.DividerItemDecoration;
import com.iconasystems.android.utils.KeyAdapter;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.view.IconicsImageView;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class KeysFragment extends Fragment {
    private RecyclerView mKeysList;
    private KeyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Lock> lockList = new ArrayList<>();

    public KeysFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_keys, container, false);
        mKeysList = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mKeysList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mKeysList.setLayoutManager(mLayoutManager);
        mKeysList.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ParseQuery<Lock> lockParseQuery = ParseQuery.getQuery("Lock");
        lockParseQuery.whereEqualTo("owner", User.getCurrentUser());
        lockParseQuery.findInBackground(new FindCallback<Lock>() {
            @Override
            public void done(List<Lock> locks, ParseException e) {
                if (e == null) {
                    mKeysList.setAdapter(new KeyAdapter(getActivity(), locks, new KeyAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Lock lock) {
                            //Toast.makeText(getContext(), "Item Clicked = " + lock.getDoorName(), Toast.LENGTH_LONG).show();
                            Intent lockDetails = new Intent(getActivity(), KeyDetailsActivity.class);
                            String lock_id = lock.getId();
                            lockDetails.putExtra("lock_id", lock_id);
                            startActivity(lockDetails);
                        }
                    }));
                } else {
                    e.printStackTrace();
                }
            }
        });

        /*ParseQuery<User> userParseQuery = ParseQuery.getQuery("User");
        userParseQuery.whereEqualTo("username", "mutonicrispus");
        userParseQuery.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> objects, ParseException e) {
                for (int i = 0; i < objects.size(); i++) {
                    User user = objects.get(i);
                    Log.d("Other user name", user.getFirstName());

                }
            }
        });*/
    }

    public boolean isInternetEnabled() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo.isConnected() && networkInfo != null) {
            return true;
        } else {
            return false;
        }
    }

}
