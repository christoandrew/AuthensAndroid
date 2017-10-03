package com.iconasystems.android.authensandroid;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.iconasystems.android.models.Lock;
import com.iconasystems.android.models.SharedLock;
import com.iconasystems.android.models.User;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseUser;

/**
 * Created by Alex on 2/4/2016.
 */
public class AuthensRealm extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Firebase.setAndroidContext(this);
        //Firebase.getDefaultConfig().setPersistenceEnabled(true);
        Parse.enableLocalDatastore(this);
        ParseUser.registerSubclass(User.class);
        ParseObject.registerSubclass(Lock.class);
        ParseObject.registerSubclass(SharedLock.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.parse_app_id))
                .clientKey(getString(R.string.parse_client_key))
                .server("http://10.0.3.2:1337/parse/")
                .build());
        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
        ParseInstallation.getCurrentInstallation().saveEventually();
        ParseAnalytics.trackAppOpenedInBackground(null);
        ParsePush.subscribeInBackground("Giants");
        initImageLoader(this);

    }


    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }
}
