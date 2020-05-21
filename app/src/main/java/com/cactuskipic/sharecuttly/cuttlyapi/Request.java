package com.cactuskipic.sharecuttly.cuttlyapi;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Request{
    
    private static final String APIURL = "https://cutt.ly/api/api.php?";
    private final String tokenID;
    private HashMap<String,String> arguments = new HashMap<>();
    
    public Request(String tokenID){
        this.tokenID = tokenID;
    }
    
    public void addArgument(String name, String value){
        arguments.put(name, value);
    }
    
    public URL makeURL(){
        StringBuilder strB = new StringBuilder();
        
        strB.append(APIURL);
        strB.append("key=");
        strB.append(tokenID);
        
        for(Map.Entry<String, String> entry:arguments.entrySet()){
            strB.append("&");
            strB.append(Uri.encode(entry.getKey()));
            strB.append("=");
            strB.append(Uri.encode(entry.getValue()));
        }
    
        try{
            return new URL(strB.toString());
        }catch(MalformedURLException e){
            e.printStackTrace();
            return null;
        }
    }
    
}
