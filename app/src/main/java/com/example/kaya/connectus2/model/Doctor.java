package com.example.kaya.connectus2.model;


public class Doctor {
private String mFirstName;
private String mLastName;
private String mUid;
private String mLocation_slug;
private String mTitle;
private String mName;
private String mPracticeName;
private String mBio;


public Doctor(String firstName, String lastName, String uid, String location_slug, String title, String name, String practiceName, String bio) {
    this.mFirstName = firstName;
    this.mLastName = lastName;
    this.mUid = uid;
    this.mLocation_slug = location_slug;
    this.mTitle = title;
    this.mName = name;
    this.mPracticeName = practiceName;
    this.mBio = bio;

}

public String getmName() {
    return mName;
}

public String getName() {
    return mName;
}

public String getUid() {
    return mUid;
}

public String getLocation() {
    return mLocation_slug;
}

public String getTitle() {
    return mTitle;
}

public String getPracticeName() {
    return mPracticeName;
}
public String getBio() {
    return mBio;
}
}



