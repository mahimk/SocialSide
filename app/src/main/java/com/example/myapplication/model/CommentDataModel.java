package com.example.myapplication.model;

import io.realm.RealmObject;

public class CommentDataModel extends RealmObject {


   int id;

    String name, comment, timpstamp,ProfilePic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimpstamp() {
        return timpstamp;
    }

    public void setTimpstamp(String timpstamp) {
        this.timpstamp = timpstamp;
    }
    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }
}


