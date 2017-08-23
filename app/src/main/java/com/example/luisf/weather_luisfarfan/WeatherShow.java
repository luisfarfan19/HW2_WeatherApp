package com.example.luisf.weather_luisfarfan;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class WeatherShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_show);
        Bundle bundle = getIntent().getExtras();
        final String city = bundle.getString("cityName");

        final TextView tvWeatherCity = (TextView) findViewById(R.id.tvWeatherCity);
        final String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=0827e9ccde7840b33bd4bf8aae027c0c";
        //tvWeatherCity.setText(url);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject mainObject =  new JSONObject(response);
                            //Getting the Name of the City
                            tvWeatherCity.setText(city);

                            //Getting the temperature in °C
                            JSONObject weatherMainObject = mainObject.getJSONObject("main");
                            double temp= weatherMainObject.getDouble("temp");
                            temp -= 273.15;
                            DecimalFormat df = new DecimalFormat("#.0");
                            String tempFormated = df.format(temp);
                            tvWeatherCity.append("\n" + tempFormated + "°C");

                            //Getting Description
                            JSONArray weatherArray = mainObject.getJSONArray("weather");
                            JSONObject descriptionArray = weatherArray.getJSONObject(0);
                            String description = descriptionArray.getString("description");
                            tvWeatherCity.append("\n\n" + description);
                            description = description.replaceAll(" ", "_").toLowerCase();

                            //Image Handling
                            ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
                            try{
                                InputStream stream = getAssets().open(description+".jpg");
                                Drawable drawable = Drawable.createFromStream(stream, null);
                                ivImage.setImageDrawable(drawable);
                            }catch (IOException ioe){
                                ioe.printStackTrace();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                Log.d("log2=", error.toString());
                Log.e("log3=", error.toString());
            }
        });

        requestQueue.add(stringRequest);

    }
}
