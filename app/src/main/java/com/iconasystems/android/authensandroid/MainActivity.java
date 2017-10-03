package com.iconasystems.android.authensandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iconasystems.android.models.SharedLock;
import com.iconasystems.android.models.User;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.ActionItemBadgeAdder;
import com.mikepenz.actionitembadge.library.utils.BadgeStyle;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private static final int SAMPLE2_ID = 34536;
    private BadgeStyle style = ActionItemBadge.BadgeStyles.DARK_GREY.getStyle();
    private BadgeStyle bigStyle = ActionItemBadge.BadgeStyles.DARK_GREY_LARGE.getStyle();
    private int badgeCount = 10;
    private int badgeDrawableCount = 100000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //       List<String> subscribedChannels = ParseInstallation.getCurrentInstallation().getList("channels");
//        Log.d(getClass().getSimpleName(), subscribedChannels.toString());

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        // Query the users



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.inflateMenu(R.menu.menu_drawer);
            setupDrawerContent(navigationView);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

        Bitmap file = BitmapFactory.decodeResource(this.getResources(), R.drawable.user, options);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        file.compress(Bitmap.CompressFormat.PNG, 100, stream);
        // get byte array here
        byte[] byteArray = stream.toByteArray();

        ParseFile imageFile = new ParseFile("profile.png", byteArray);

        /*final User user = new User();
        user.setFirstName("Mutoni");
        user.setLastName("Crispus");
        user.setUsername("chrismutoni");
        user.setEmail("mutonicrispus@gmail.com");
        user.setPassword("mutonicrispus");
        user.setPhoneNumber("0788057839");
        user.setProfilePicture(imageFile);

        imageFile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e!=null){
                                e.printStackTrace();
                                Log.e("Error", "Error code = " + e.getCode());

                            }
                        }
                    });
                } else{
                    e.printStackTrace();
                }
            }
        });*/


        /*Lock lock = new Lock();
        lock.setOwner((User) User.getCurrentUser());
        lock.setSimNumber("0788057839");
        lock.setDoorName("Penthouse front door");
        lock.setId();
        lock.setAuthToken();
        lock.setDefaultState();
        lock.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) e.printStackTrace();
            }
        });*/


//        Log.d(getClass().getSimpleName() + "UUID = ", User.getCurrentUser().toString());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //you can add some logic (hide it if the count == 0)
        //you can add some logic (hide it if the count == 0)
        if (badgeCount > 0) {
            ActionItemBadge
                    .update(this, menu.findItem(R.id.notifications_item), FontAwesome.Icon.faw_bell_o, ActionItemBadge.BadgeStyles.GREEN, badgeCount);
        } else {
            ActionItemBadge.hide(menu.findItem(R.id.notifications_item));
        }

        //If you want to add your ActionItem programmatically you can do this too. You do the following:
        new ActionItemBadgeAdder().act(this).menu(menu).title("Notifications").itemDetails(0, SAMPLE2_ID, 1).showAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS).add(bigStyle, 1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            case R.id.action_logout:
                User.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new FeedFragment(), "Feed");
        adapter.addFragment(new KeysFragment(), "Keys");
        adapter.addFragment(new SharedFragment(), "Shared");
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        View headerView = navigationView.getHeaderView(0);

        User currentUser = (User) User.getCurrentUser();

        if (currentUser != null) {
            String username = currentUser.getUsername();
            ParseFile profilePhoto = currentUser.getProfilePicture();
            DisplayImageOptions options;
            TextView headerUsername = (TextView) headerView.findViewById(R.id.user_header_username);
            headerUsername.setText(username);

            final ImageView headerProfile = (ImageView) headerView.findViewById(R.id.user_header_profile_photo);

            if (profilePhoto != null) {

                options = new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .considerExifParams(true)
                        .displayer(new CircleBitmapDisplayer(Color.WHITE, 5))
                        .build();

                ImageLoader.getInstance().displayImage(profilePhoto.getUrl(), headerProfile, options);
            }
            ;
        }


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
}
