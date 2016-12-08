package com.zarkov.petar;





import io.realm.RealmObject;
//city class with votes and names

public class City extends RealmObject {
    //declare variables
    private String name;
    private long votes;
    //getters and setters for those variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

}