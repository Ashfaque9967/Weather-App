import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class WeatherApp {

    // un-comment after writing getLocationData() -> ashhab
//    public static JSONObject getWeatherData(String locationName){
//        JSONArray locationData = getLocationData(locationName);
//
//        JSONObject location = (JSONObject) locationData.get(0);
//        double latitude = (double) location.get("latitude");
//        double longitude = (double) location.get("longitude");
//
//        String urlString = "https://api.open-meteo.com/v1/forecast?" +
//                "latitude=" + latitude + "&longitude=" + longitude +
//                "&hourly=temperature_2m,relativehumidity_2m,weathercode,windspeed_10m&timezone=America%2FLos_Angeles";
//
//        try{
//            HttpURLConnection conn = fetchApiResponse(urlString);
//
//            if(conn.getResponseCode() != 200){
//                System.out.println("Error: Could not connect to API");
//                return null;
//            }
//
//            StringBuilder resultJson = new StringBuilder();
//            Scanner scanner = new Scanner(conn.getInputStream());
//            while(scanner.hasNext()){
//                resultJson.append(scanner.nextLine());
//            }
//
//            scanner.close();
//
//            conn.disconnect();
//
//            JSONParser parser = new JSONParser();
//            JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));
//
//            JSONObject hourly = (JSONObject) resultJsonObj.get("hourly");
//
//            JSONArray time = (JSONArray) hourly.get("time");
//
//            JSONArray temperatureData = (JSONArray) hourly.get("temperature_2m");
//            double temperature = (double) temperatureData.get(index);
//
//            JSONArray weathercode = (JSONArray) hourly.get("weathercode");
//
//            JSONArray relativeHumidity = (JSONArray) hourly.get("relativehumidity_2m");
//            long humidity = (long) relativeHumidity.get(index);
//
//            JSONArray windspeedData = (JSONArray) hourly.get("windspeed_10m");
//            double windspeed = (double) windspeedData.get(index);
//
//            JSONObject weatherData = new JSONObject();
//            weatherData.put("temperature", temperature);
//            weatherData.put("weather_condition", weatherCondition);
//            weatherData.put("humidity", humidity);
//            weatherData.put("windspeed", windspeed);
//
//            return weatherData;
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    // getLocationData() -> ashhab

    private static HttpURLConnection fetchApiResponse(String urlString){
        try{
            // attempt to create connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // set request method to get
            conn.setRequestMethod("GET");

            // connect to our API
            conn.connect();
            return conn;
        }catch(IOException e){
            e.printStackTrace();
        }

        // could not make connection
        return null;
    }


    // findIndexOfCurrentTime() -> umer

     private static String getCurrentTime(){
         // get current date and time
         LocalDateTime currentDateTime = LocalDateTime.now();

         // format date to be 2023-09-02T00:00 (this is how is is read in the API)
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");

         // format and print the current date and time
         String formattedDateTime = currentDateTime.format(formatter);

         return formattedDateTime;
     }

    private static String convertWeatherCode(long weathercode){
        String weatherCondition = "";
        if(weathercode == 0L){
            // clear
            weatherCondition = "Clear";
        }else if(weathercode > 0L && weathercode <= 3L){
            // cloudy
            weatherCondition = "Cloudy";
        }else if((weathercode >= 51L && weathercode <= 67L)
                || (weathercode >= 80L && weathercode <= 99L)){
            // rain
            weatherCondition = "Rain";
        }else if(weathercode >= 71L && weathercode <= 77L){
            // snow
            weatherCondition = "Snow";
        }

        return weatherCondition;
    }
}






