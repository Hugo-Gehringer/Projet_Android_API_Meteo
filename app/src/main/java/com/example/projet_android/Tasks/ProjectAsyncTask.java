package com.example.projet_android.Tasks;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.projet_android.MainActivity;
import com.example.projet_android.WeatherPerCityActivity;

public class ProjectAsyncTask extends AsyncTask<Void, Void, String>
{
    @SuppressLint("StaticFieldLeak")
    private final Context currentContext;
    private final Bundle nextActivityParameter;

    public ProjectAsyncTask(Context currentContext, Bundle nextActivityParameter)
    {
        this.currentContext = currentContext;
        this.nextActivityParameter = nextActivityParameter;
    }

    @Override
    protected String doInBackground(Void... voids)
    {
        try {
            Thread.sleep(100);
            return "OK";
        } catch(InterruptedException e) {
            return "KO";
        }
    }

}
