package com.cactuskipic.sharecuttly.history;

import android.content.Context;
import android.widget.Toast;

import com.cactuskipic.sharecuttly.CuttlyActivity;
import com.cactuskipic.sharecuttly.MainActivity;
import com.cactuskipic.sharecuttly.cuttlyapi.APIRequest;
import com.cactuskipic.sharecuttly.cuttlyapi.ContainerCuttlyServiceActivity;
import com.cactuskipic.sharecuttly.cuttlyapi.Request;
import com.cactuskipic.sharecuttly.cuttlyapi.Response;
import com.cactuskipic.sharecuttly.cuttlyapi.ResponseURL;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class RetrieveResponse{
    
    private static HashMap<String, ResponseURL> stockResponse = new HashMap<>();
    
    public static ResponseURL getResponseURL(Context context,String name){
        ResponseURL res = stockResponse.get(name);
        if(res != null)
            return res;
    
        File file = new File(context.getDir(context.getApplicationInfo().processName, Context.MODE_PRIVATE) + MainActivity.saveLocation + name);
        Gson gson = new Gson();
        StringBuilder builder = new StringBuilder();
        String buffer;
        try{
            BufferedReader in = new BufferedReader(new FileReader(file));
            
            while((buffer = in.readLine()) != null)
                builder.append(buffer);
            in.close();
            
            res = gson.fromJson(builder.toString(), ResponseURL.class);
            
            if(res != null)
                stockResponse.put(name, res);
            
        }catch(IOException e){
            Toast.makeText(context, "Error while oppening history item", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return res;
    }
    
    public static void getResponseAna(CuttlyActivity activity, ResponseURL responseURL){
        if(responseURL == null){
            Toast.makeText(activity.getApplicationContext(),"Impossible to retrieve stats, this item is corrupted", Toast.LENGTH_SHORT).show();
            return;
        }
        
        Request request = new Request(MainActivity.tokenID);
        
        request.addArgument("stats", responseURL.getShortLink());
        
        APIRequest apiRequest = new APIRequest(new ContainerCuttlyServiceActivity(activity));
        apiRequest.execute(request);
    
        Toast.makeText(activity.getApplicationContext(),"Retrieving stats for this URL", Toast.LENGTH_SHORT).show();
    }
    
}
