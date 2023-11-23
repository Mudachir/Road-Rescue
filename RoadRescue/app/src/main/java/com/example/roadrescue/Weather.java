package com.example.roadrescue;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class Weather extends AppCompatActivity {
    EditText etCity, etCountry;
    TextView tvResult;
    private final String apiKey = "c6cc42083f57dcef1c231ddb79c935db"; // Replace with your OpenWeatherMap API key
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
        tvResult = findViewById(R.id.tvResult);
    }

    public void getWeatherDetails(View view) {
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();

        if (city.isEmpty()) {
            tvResult.setText("City field cannot be empty");
        } else {
            String apiUrl;
            if (!country.isEmpty()) {
                apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + country + "&appid=" + apiKey;
            } else {
                apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
            }

            StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        // Parse the JSON response
                        JSONObject jsonResponse = new JSONObject(response);
                        String temperature = jsonResponse.getJSONObject("main").getString("temp");
                        double tempInCelsius = Double.parseDouble(temperature) - 273.15; // Convert temperature to Celsius
                        String weatherDescription = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");

                        // Display the weather details
                        String result = "Temperature: " + df.format(tempInCelsius) + "°C\nWeather: " + weatherDescription;
                        tvResult.setText(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        tvResult.setText("Error parsing response");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}
