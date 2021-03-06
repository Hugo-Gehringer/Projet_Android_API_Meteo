package com.example.projet_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projet_android.Tasks.ProjectAsyncTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editTextCityName=(EditText) findViewById(R.id.inputCitySearch);

        Button buttonCity=(Button) findViewById(R.id.ButtonCitySearch);

        buttonCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = editTextCityName.getText().toString();
                if (cityName.isEmpty()){
                    Log.d(TAG,"Enter a Name");
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter a city name", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, WeatherPerCityActivity.class);
                    Bundle bundle = new Bundle(1);
                    bundle.putString(WeatherPerCityActivity.INPUT_PARAMETER, cityName);
                    intent.putExtras(bundle);
                    MainActivity.this.startActivity(intent);
                }

            }
        });

    }
}