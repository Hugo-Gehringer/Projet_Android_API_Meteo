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
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editTextCityName=(EditText) findViewById(R.id.inputCitySearch);
        
        editTextCityName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    buttonCity.performClick();
                    return true;
                }
                return false;
            }
        });

        Button buttonCity=(Button) findViewById(R.id.ButtonCitySearch);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext()).
                build();
        ImageLoader.getInstance().init(configuration);

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
