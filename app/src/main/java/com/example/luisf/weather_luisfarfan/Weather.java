package com.example.luisf.weather_luisfarfan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Weather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ListView lvCities = (ListView) findViewById(R.id.lvCities);
        ArrayList<String> citiesList = new ArrayList<String>();
        citiesList.add("Barcelona");
        citiesList.add("Toluca");
        citiesList.add("Monterrey");
        citiesList.add("Budapest");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, citiesList
        );
        lvCities.setAdapter(adapter);

        lvCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(Weather.this,WeatherShow.class);
                intent.putExtra("cityName",city);
                startActivity(intent);
            }
        });
    }
}
