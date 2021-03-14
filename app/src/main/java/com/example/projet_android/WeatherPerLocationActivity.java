package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projet_android.adapters.WeatherCityAdapter;
import com.example.projet_android.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class WeatherPerLocationActivity extends AppCompatActivity {
    private static final String TAG = WeatherPerLocationActivity.class.getSimpleName();
    public static final String INPUT_Longitude = "input_parameter";
    public static final String INPUT_Latitude = "";

    String apiID = "&appid=a34aaab7afe9e436a612254a3cfe4670";

    private  String longitude;
    private  String latitude;
    List<Weather> listeWeather= new ArrayList<Weather>();

    public static final int RESULT_OK = 0;
    public static final int RESULT_KO = 1;

    private static String language;
    private static Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_per_city);

        if (Locale.getDefault().getDisplayLanguage().equals("français")){
            language = "&lang=fr";
            locale = Locale.FRANCE;
        }else {
            language = "&lang=en";
            locale = Locale.ENGLISH;
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        longitude = getIntent().getExtras().getString(INPUT_Longitude);
        latitude = getIntent().getExtras().getString(INPUT_Latitude);

        Log.d(TAG, "Lat : " + latitude);
        Log.d(TAG, "long : " + longitude);

        //List<Game> games = new ArrayList<>();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d(TAG, "Connected to internet");

            getWeather(latitude,longitude,queue);
        }


        String string = "https://tile.openweathermap.org/map/temp_new/7/49/3.6.png?&appid=a34aaab7afe9e436a612254a3cfe4670";

    }

    public void getWeather(String latitude,String longitude,RequestQueue queue){

        Log.d(TAG, "Lat : " + latitude);
        Log.d(TAG, "long : " + longitude);

        String metrics= "&units=metric";
        String urlWeather = "https://api.openweathermap.org/data/2.5/onecall?lat="+latitude+"&lon="+longitude+"&exclude=minute,hourly,alerts"+metrics+language+apiID;
        Log.d(TAG, "url weather : " + urlWeather);
        StringRequest stringRequestWeather = new StringRequest(Request.Method.GET, urlWeather, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Response : " + response);

                try {
                    JSONObject weatherObj = new JSONObject(response);
                    JSONArray jsonArray=weatherObj.getJSONArray("daily");
                    Log.d(TAG, "Response : " + jsonArray);

                    for (int i=0; i<jsonArray.length();i++) {

                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        long unixSeconds= jsonObject.getInt("dt");
                        Date date = new Date(unixSeconds*1000L);
                        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEEE d MMM", locale);
                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // give a timezone reference for formating (see comment at the bottom
                        String jour = simpleDateFormat.format(date);


                        Log.d(TAG, "dt : " + jour);

                        int temperatureMax = jsonObject.getJSONObject("temp").getInt("max");
                        String tempMax =getString(R.string.day)+ temperatureMax+"°C";
                        Log.d(TAG, "tempJ : " + tempMax);
                        int temperatureMin = jsonObject.getJSONObject("temp").getInt("min");
                        String tempMin = getString(R.string.night)+temperatureMin+"°C";
                        Log.d(TAG, "tempNight : " + tempMin);

                        String icon= jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
                        Log.d(TAG, "arraWTH : " + icon);

                        String description= jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                        Log.d(TAG, "arraWTH : " + description);


                        Weather weather = new Weather(jour,tempMax,tempMin,description,icon);
                        listeWeather.add(weather);
                    }

                    ListView listview = (ListView) findViewById(R.id.listView);
                    WeatherCityAdapter weatherCityAdapter = new WeatherCityAdapter(WeatherPerLocationActivity.this,listeWeather);
                    listview.setAdapter(weatherCityAdapter);

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
        queue.add(stringRequestWeather);
    }
}