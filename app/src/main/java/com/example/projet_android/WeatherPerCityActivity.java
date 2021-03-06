package com.example.projet_android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projet_android.model.City;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeatherPerCityActivity extends AppCompatActivity {
    private static final String TAG = WeatherPerCityActivity.class.getSimpleName();
    public static final String INPUT_PARAMETER = "input_parameter";
    String apiID = "&appid=a34aaab7afe9e436a612254a3cfe4670";

    public static final int RESULT_OK = 0;
    public static final int RESULT_KO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_per_city);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.openweathermap.org/data/2.5/weather?";
        String input = getIntent().getExtras().getString(INPUT_PARAMETER);
        String cityRequest = "q=" + input;
        String metrics= "&units=metric";

        url += cityRequest + apiID+metrics;
        //List<Game> games = new ArrayList<>();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d(TAG, "Connected to internet");

            Log.d(TAG, "URL: " + url);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d(TAG, "Response : " + response);

                    try {
                        JSONObject weatherObj = new JSONObject(response);
                        City city = new City(weatherObj.getInt("id"), weatherObj.getString("name"),weatherObj.getJSONObject("coord").getString("lon"),weatherObj.getJSONObject("coord").getString("lon"));
                        Log.d(TAG,city.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "ERROR", e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Erreur de requête", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            queue.add(stringRequest);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Non connecté à internet", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
    }
}