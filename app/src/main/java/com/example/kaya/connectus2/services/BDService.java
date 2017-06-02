package com.example.kaya.connectus2.services;
import android.util.Log;

import com.example.kaya.connectus2.ui.Constants;
import com.example.kaya.connectus2.model.Doctor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class BDService {

public static void findBySymptom(String symptom, Callback callback) {

    OkHttpClient client = new OkHttpClient();

    String url = Constants.BD_TEST_URL;

    Log.d("url", url);

    Request request = new Request.Builder()
            .url(url)
            .build();

    Call call = client.newCall(request);
    call.enqueue(callback);
}

public ArrayList<Doctor> processResults(Response response) {
    ArrayList<Doctor> doctor = new ArrayList<>();
    try {
        String jsonData = response.body().string();
        if (response.isSuccessful()) {

            JSONObject doctorJson = new JSONObject(jsonData);
            String name = doctorJson.getJSONArray("practices").getJSONObject(0).getString("name");
            String location_slug = doctorJson.getJSONObject("practices").getString("location_slug");
            String uid = doctorJson.getJSONObject("practices").getString("uid");
            String title = doctorJson.getJSONObject("profile").getString("title");
            String practiceName = doctorJson.getJSONObject("practices").getString("practiceName");
            String firstName = doctorJson.getJSONArray("profile").getJSONObject(0).getString("first_name");
            String lastName = doctorJson.getJSONArray("profile").getJSONObject(0).getString("last_name");

            Doctor instanceOf = new Doctor(firstName, lastName, name, uid, location_slug, title, practiceName);
            doctor.add(instanceOf);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return doctor;
}
}
