package com.cactuskipic.sharecuttly.cuttlyapi;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.cactuskipic.sharecuttly.CuttlyActivity;
import com.cactuskipic.sharecuttly.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class APIRequest extends AsyncTask<Request, Void, Response> {
    
    private CuttlyActivity activity;
    
    public APIRequest(CuttlyActivity activity){
        this.activity = activity;
    }
    
    @Override
    protected Response doInBackground(Request... requests){
        StringBuffer response = new StringBuffer();
        URL url = requests[0].makeURL();
        
        try{
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
    
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            
            while((inputLine = in.readLine())!= null){
                response.append(inputLine);
            }
            in.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
        
        return convertToResponse(response.toString());
    }
    
    private Response convertToResponse(String json){
        Gson gson = new GsonBuilder().registerTypeAdapter(ResponseAna.class, new AnaDeserialiser()).create();
        Log.i(MainActivity.MARK, json);
        if(json.startsWith("{\"url\""))
            return gson.fromJson(json, ResponseURL.class);
        if(json.startsWith("{\"stats\""))
            return gson.fromJson(json, ResponseAna.class);
        return null;
    }
    
    public void onPostExecute(Response response){
            response.setTime(new Date().getTime());
            activity.GetPostExecute(response);
    }
}
