package com.example.projet_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.projet_android.R;
import com.example.projet_android.model.Weather;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class WeatherCityAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Weather> weathers;

    public WeatherCityAdapter(Context context, List<Weather> weathers) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.weathers = weathers;
    }

    @Override
    public int getCount() {
        return this.weathers.size();
    }

    @Override
    public Object getItem(int position) {
        return this.weathers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.weathers.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = this.layoutInflater.inflate(R.layout.weather_list_item, parent, false);

        ImageView weatherImage = (ImageView) itemView.findViewById(R.id.weather_icon);
        TextView weatherDay = (TextView) itemView.findViewById(R.id.weather_day);
        TextView weatherMaxTemp = (TextView) itemView.findViewById(R.id.weather_tempDay);
        TextView weatherMinTemp = (TextView) itemView.findViewById(R.id.weather_tempNight);
        TextView weatherDescription = (TextView) itemView.findViewById(R.id.weather_description);

         // Get singleton instance
        ImageLoader.getInstance().displayImage(this.weathers.get(position).getIcon(), weatherImage);

        weatherDay.setText(this.weathers.get(position).getDay());
        weatherMaxTemp.setText(this.weathers.get(position).getMaxTemp());
        weatherMinTemp.setText(this.weathers.get(position).getMinTemp());
        weatherDescription.setText(this.weathers.get(position).getDescription());

        return itemView;
    }

}
