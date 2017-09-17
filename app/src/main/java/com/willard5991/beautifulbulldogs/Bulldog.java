package com.willard5991.beautifulbulldogs;


import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by willard5991 on 9/12/2017.
 */

public class Bulldog extends RealmObject {
    private String id;
    private String name;
    private String age;
    public RealmList<Vote> votes;
    private byte[] image;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public RealmList<Vote> getVotes(){
        return votes;
    }

    public void setVotes(RealmList<Vote> votes){
        this.votes = votes;
    }

    public void appendVote(Vote vote){
        this.votes.add(vote);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public byte[] getImage(){
        return image;
    }

    public void setImage(byte[] image){
        this.image = image;
    }
}
