package com.example.kaya.connectus2;

import com.example.kaya.connectus2.BuildConfig;

public class Constants {
 public static final String BETTERDOCTOR_KEY = BuildConfig.BETTERDOCTOR_KEY;
 public static final String BD_BASE_URL = "https://api.betterdoctor.com/2016-03-01/doctors?query=";
 public static final String BD_BASE2_URL = "&location=45.5231%2C-122.6765%2C%205&user_location=45.5231%2C-122.6765&skip=0&limit=20&user_key=dec62bc90fcd75b60056e4c068f708b5";
public static final String BD_TEST_URL = "https://api.betterdoctor.com/2016-03-01/doctors?query=diabetes&location=37.773%2C-122.413%2C100&user_location=37.773%2C-122.413&skip=0&limit=10&user_key=dec62bc90fcd75b60056e4c068f708b5";
public static final String BD_LOCATION_PARAM = "location";
public static final String BD_SYMPTOM_PARAM = "symptom";
public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
public static final String API_KEY_QUERY_PARAMETER = "user_key";
public static final String FIREBASE_CHILD_DOCTORS = "doctors";
public static final String FIREBASE_CHILD_AILMENTS = "ailments";
public static final String PREFERENCES_SPECIALTY_KEY = "specialtySpinnerSelection";
public static final String PREFERENCES_CITY_KEY = "city";
public static final String PREFERENCES_STATE_KEY = "stateSpinnerSelection";
public static final String BD_NOTE_DATE = "noteDate";
public static final String BD_NOTE_BODY = "firebaseNoteBody";
}
