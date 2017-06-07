package com.example.kaya.connectus2.services;
import android.telephony.PhoneNumberUtils;
import android.util.Log;

import com.example.kaya.connectus2.Constants;
import com.example.kaya.connectus2.model.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

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
            JSONArray bdArray = (JSONArray) bdJSON.get("data");
            for (int i = 0; i < bdArray.length(); i++) {
                JSONObject doctorJSON = bdArray.getJSONObject(i);
                String name = doctorJSON.getJSONArray("practices").getJSONObject(0).getString("name");
             

                ArrayList<String> phone = new ArrayList<>();
                JSONArray phoneJSON = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONArray("phones");
                for (int y = 0; y < phoneJSON.length(); y++) {
                    String number = phoneJSON.getJSONObject(y).getString("number");
                    String formattedNumber = PhoneNumberUtils.formatNumber(number, Locale.getDefault().getCountry());
                    phone.add(formattedNumber);
                }

                String street = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("street");
                String street2;
                try {
                    street2 = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("street2");
                } catch (JSONException e) {
                    street2 = "";
                }

                String city = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("city");
                String state = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("state");
                String zip = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("zip");


                String bio = doctorJSON.getJSONObject("profile").getString("bio");
                String firstName = doctorJSON.getJSONObject("profile").getString("first_name");
                String lastName = doctorJSON.getJSONObject("profile").getString("last_name");
                String drBio = doctorJSON.getJSONObject("profile").getString("bio");
                String imageUrl = doctorJSON.getJSONObject("profile").getString("image_url");
                String drTitle = doctorJSON.getJSONObject("profile").getString("title");
                Log.d("firstName", firstName);

                Doctor instanceOf = new Doctor(firstName, lastName, drTitle, drBio, imageUrl);
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
