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
private String mImageUrl;


public Doctor(String firstName, String lastName, String title, String bio, String url) {
    this.mFirstName = firstName;
    this.mLastName = lastName;
    //this.mUid = uid;
    //this.mLocation_slug = location_slug;
    this.mTitle = title;
   // this.mName = name;
    //this.mPracticeName = practiceName;
    this.mBio = bio;
    this.mImageUrl = url;

}

public String getFirstName(){
    return mFirstName;
}
public String getLastName(){
    return mLastName;
}

public String getName() {
    return mName;
}


public String getUid() {
    return mUid;
}

public String getImageUrl(){
    return mImageUrl;
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



