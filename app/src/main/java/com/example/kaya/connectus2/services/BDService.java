package com.example.kaya.connectus2.services;
import android.util.Log;

import com.example.kaya.connectus2.ui.Constants;
import com.example.kaya.connectus2.model.Doctor;

import org.json.JSONArray;
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
    String mSymptom = symptom;
    String baseUrl = Constants.BD_BASE_URL;
    String baseUrl2 = Constants.BD_BASE2_URL;
    String constructedUrl = baseUrl + mSymptom + baseUrl2;

    Log.d("url", url);
    Log.d("constructedUrl", constructedUrl);

    Request request = new Request.Builder()
            .url(url)
            .build();

    Call call = client.newCall(request);
    call.enqueue(callback);
}

public ArrayList<Doctor> processResults(Response response) {
    ArrayList<Doctor> doctors = new ArrayList<>();

    try {
        String jsonData = response.body().string();
        if (response.isSuccessful()) {

            JSONObject bdJSON = new JSONObject(jsonData); //API response
            JSONArray practicesArray = (JSONArray) bdJSON.get("practices");
            for (int i = 0; i < practicesArray.length(); i++) {
                JSONObject doctorJSON = practicesArray.getJSONObject(i);
                String title = doctorJSON.getString("title");

                String firstName = doctorJSON.getString("first_name");
                String lastName = doctorJSON.getString("last_name");
                String bio = doctorJSON.getString("bio");
                String imageUrl = doctorJSON.getString("image_url");
                Log.d("firstName", firstName);

                Doctor instanceOf = new Doctor(firstName, lastName, title,  bio, imageUrl);
                doctors.add(instanceOf);
            }

        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return doctors;
    }
}
