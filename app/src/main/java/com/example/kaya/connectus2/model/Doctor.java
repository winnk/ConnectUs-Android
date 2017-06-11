package com.example.kaya.connectus2.model;

import org.parceler.Parcel;
import java.util.ArrayList;

@Parcel
public class Doctor {
     String title;
     String name;
     String bio;
     String imageUrl;
     String specialty;
ArrayList<String> phone = new ArrayList<>();
     String city;
     String state;
     String zip;
     String address1;
     String address2;
     String pushId;
     Double latitude;
     Double longitude;
     String firstName;
     String lastName;
     String drTitle;


public Doctor() {}

public Doctor(String name, String title, String bio, String url, String address1, String address2, String city, String state, String zip, ArrayList<String> phone, String specialty, Double latitude, Double longitude, String firstName, String lastName, String drTitle) {
    this.title = title;
    this.firstName = firstName;
    this.lastName = lastName;
    this.drTitle = drTitle;
    this.name = name;
    this.bio = bio;
    this.imageUrl = url;
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.phone = phone;
    this.specialty  = specialty;
    this.latitude = latitude;
    this.longitude = longitude;
}

public String getName() {
    return name;
}
public String getFirstName(){return firstName;}
public String getLastName(){return lastName;}
public String getTitle(){return title;}
public String getImageUrl(){
    return imageUrl;
}
public String getLargeImageUrl(String imageUrl) {
    String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
    return largeImageUrl;
}
public String getdrTitle() {return drTitle;}
public String getBio() {
    return bio;
}
public String getAddress1() {return address1;}
public String getAddress2(){return address2;}
public String getCity() {return city;}
public String getState() {return state;}
public String getZip() {return zip;}
public ArrayList<String> getPhone() {return phone;}
public String getSpecialty() {return specialty;}
public Double getLatitude(){return latitude;}
public Double getLongitude(){return longitude;}
public String getPushId(){return pushId;}
public void setPushId(String pushId){this.pushId = pushId;}


}




