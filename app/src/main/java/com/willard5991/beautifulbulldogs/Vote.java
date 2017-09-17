package com.willard5991.beautifulbulldogs;

import io.realm.RealmObject;

/**
 * Created by willard5991 on 9/17/2017.
 */

public class Vote extends RealmObject {
    private User owner;
    private int rating;

    public void setOwner(User owner){
        this.owner = owner;
    }

    public User getOwner(){
        return owner;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }
}
