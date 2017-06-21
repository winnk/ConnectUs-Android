package com.example.kaya.connectus2.model;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;


import org.parceler.Parcel;

import java.util.Date;

import static android.R.attr.format;


/**
 * TODO: Add a class header comment!
 */
@Parcel
public class Note {

String date;
String body;
String title;
private String pushId;
String index;
    String imageUrl;

public Note(){}

public Note (String noteBody, String noteTitle){
    this.body = noteBody;
    this.title = noteTitle;
    this.imageUrl = "http://blog.allo.ua/wp-content/uploads/ColorNote-logotip.png";

    java.util.Calendar cal = java.util.Calendar.getInstance();
    String year = cal.get(java.util.Calendar.YEAR) + "";
    String month = (cal.get(java.util.Calendar.MONTH) + 1) + "";
    String day = cal.get(java.util.Calendar.DAY_OF_MONTH) + "";
    Log.d("MainActivity", "Current Timestamp: " + format);
    this.date = month + " / " +day+ " / " + year;
    this.index = "not_specified";
}

public String getNote() {
    return body;
}

public String getNoteUrl(){return imageUrl;}

public String getTitle(){
    return title;
}

public String getDate() {
    return date;
}

public String getPushId(){
    return pushId;
}

public String getIndex() {
        return index;
    }

// end getters

public void setPushId(String pushId) {
    this.pushId = pushId;
}

public void setIndex(String index) {
        this.index = index;
    }


}


