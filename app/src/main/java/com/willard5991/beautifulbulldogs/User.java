package com.willard5991.beautifulbulldogs;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by willard5991 on 9/17/2017.
 */

public class User extends RealmObject implements Serializable {
    @PrimaryKey
    private String username;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }
}
