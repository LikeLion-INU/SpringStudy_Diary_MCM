package com.example.diary.domain.openApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class WeatherController {
    @Value(("${openApi.secret}"))
    private String key;

//    @GetMapping("/weather/{lon}/{lat}")
    @GetMapping("/weather/{city}")
//    public String getWeather(@PathVariable String lat, @PathVariable String lon)
    public String getWeather(@PathVariable String city){
        StringBuilder stringBuilder = new StringBuilder();

        try {
            // String APIUrl = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + key;

            String APIUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + city +"&appid=" + key;

            URL url = new URL(APIUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader bufferedReader;

            if(urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() <= 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            }
            else {
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
            }

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();
            urlConnection.disconnect();


        } catch (Exception e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}