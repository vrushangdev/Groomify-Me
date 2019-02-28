package com.example.medico;
import android.util.Log;

import java.io.*;
import java.net.*;
import java.util.*;
import com.google.gson.*;
import com.squareup.okhttp.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Translate {
    // Instantiates the OkHttpClient.

    // This function performs a POST request.
    public String Post(String toText) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String subscriptionKey = "ec743566bc594cc7aa6398212cfe8218";
        String url = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&to=hi";
        String front = "[{'Text':' ";
        String middle = toText;
        String back = "'}]";
        String final_text = front+middle+back;
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                final_text);
        Request request = new Request.Builder()
                .url(url).post(body)
                .addHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
                .addHeader("Content-type", "application/json").build();
        Response response = client.newCall(request).execute();
        Log.d("Translate",response.toString());
        return response.body().string();
    }
    // This function prettifies the json response.
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }
    public static String getTranslatedText(String response){
        String translatedText=null;
        try {
            JSONArray array = new JSONArray(response);

            JSONObject object =  array.getJSONObject(0);
            String translations = object.getString("translations");
            Log.d("newPost",translations);
            JSONArray trans = new JSONArray(translations);
            JSONObject transobj = trans.getJSONObject(0);
            translatedText=transobj.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return translatedText;
    }
    public static String detectLanguage(String response){
        String detectedLanguage=null;
        try{
            JSONArray array = new JSONArray(response);

            JSONObject object =  array.getJSONObject(0);
            String translations = object.getString("detectedLanguage");
            JSONArray trans = new JSONArray(translations);
            JSONObject transobj = trans.getJSONObject(0);
            detectedLanguage=transobj.getString("language");




        }catch(Exception e){

            e.printStackTrace();
        }
        return detectedLanguage;
    }

}
