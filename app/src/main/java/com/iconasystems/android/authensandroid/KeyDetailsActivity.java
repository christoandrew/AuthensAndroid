package com.iconasystems.android.authensandroid;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.iconasystems.android.models.Lock;
import com.iconasystems.android.models.SharedLock;
import com.iconasystems.android.models.User;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class KeyDetailsActivity extends AppCompatActivity {
    private TextView mLockName;
    private PagerSlidingTabStrip tabs;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_details);

        //final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*toolbar.setSubtitle("last opened today");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);*/


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        tabLayout.setupWithViewPager(viewPager);

        Bundle bundle = getIntent().getExtras();
        String lock_id = bundle.getString("lock_id");
        ParseQuery<Lock> getLock = ParseQuery.getQuery("Lock");

        //mLockName = (TextView) findViewById(R.id.lock_name);

        /*Log.d("Lock Id", lock_id);
        getLock.whereEqualTo("doorId", lock_id);

        getLock.findInBackground(new FindCallback<Lock>() {
            @Override
            public void done(List<Lock> objects, ParseException e) {
                if (e == null) {
                    Lock lock = objects.get(0);
                    lock.fetchInBackground(new GetCallback<Lock>() {
                        @Override
                        public void done(Lock lock, ParseException e) {
                            mLockName.setText(lock.getDoorName());
                            toolbar.setTitle(lock.getDoorName());
                        }
                    });

                } else {
                    e.printStackTrace();
                }
            }
        });
*/

        /*ParseQuery<User> users = new ParseQuery<>("User");

        users.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    if (user != User.getCurrentUser()){
                        Log.d("Other user", user.getUsername());
                    }
                }
            }
        });*/
                /*SharedLock sharedLock = new SharedLock();
        sharedLock.setOwner((User) User.getCurrentUser());
        sharedLock.setAuthenticationToken(UUID.randomUUID().toString());
        sharedLock.*/


    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new KeysFragment(), "History");
        adapter.addFragment(new KeySharingFragment(), "Access");
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_key_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
