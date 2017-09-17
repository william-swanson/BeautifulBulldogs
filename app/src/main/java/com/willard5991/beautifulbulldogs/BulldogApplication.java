package com.willard5991.beautifulbulldogs;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by willard5991 on 9/17/2017.
 */

public class BulldogApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        Realm.init(this);
    }
}
