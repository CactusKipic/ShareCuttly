package com.cactuskipic.sharecuttly.cuttlyapi;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Set;

public class AnaDeserialiser implements JsonDeserializer<ResponseAna> {
    @Override
    public ResponseAna deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
        
        //System.out.println(json.getAsJsonObject().keySet());
        JsonObject joBase = json.getAsJsonObject().get("stats").getAsJsonObject();
        JsonObject joDevice = joBase.get("devices").getAsJsonObject();
        HashMap<String, Integer> geo;
        HashMap<String, Integer> dev;
        HashMap<String, Integer> sys;
        HashMap<String, Integer> bro;
        
        if(joDevice.get("geo") == null)
            geo = null;
        else
            geo = ExtractData(joDevice.get("geo").getAsJsonObject());
        if(joDevice.get("dev") == null)
            dev = null;
        else
            dev = ExtractData(joDevice.get("dev").getAsJsonObject());
        if(joDevice.get("sys") == null)
            sys = null;
        else
            sys = ExtractData(joDevice.get("sys").getAsJsonObject());
        if(joDevice.get("bro") == null)
            bro = null;
        else
            bro = ExtractData(joDevice.get("bro").getAsJsonObject());
        
        ResponseAna.DataAna res = new ResponseAna.DataAna(joBase.get("status").getAsInt(),
                joBase.get("fullLink").getAsString(),
                joBase.get("shortLink").getAsString(),
                joBase.get("date").getAsString(),
                joBase.get("title").getAsString(),
                joBase.get("clicks").getAsString(),
                joBase.get("facebook").getAsInt(),
                joBase.get("twitter").getAsInt(),
                joBase.get("pinterest").getAsInt(),
                joBase.get("instagram").getAsInt(),
                joBase.get("googlePlus").getAsInt(),
                joBase.get("linkedin").getAsInt(),
                joBase.get("rest").getAsInt(),
                geo,
                dev,
                sys,
                bro);
        
        return new ResponseAna(res);
    }
    
    private HashMap<String, Integer> ExtractData(JsonObject json){
        HashMap<String, Integer> HM = new HashMap<>();
        Set<String> set = json.keySet();
        for(String str:set){
            HM.put(json.get(str).getAsJsonObject().get("tag").getAsString(), Integer.parseInt(json.get(str).getAsJsonObject().get("clicks").getAsString()));
        }
        return HM;
    }
}
