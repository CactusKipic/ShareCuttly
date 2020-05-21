package com.cactuskipic.sharecuttly.cuttlyapi;

import java.lang.reflect.Modifier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class Response{
    
    private long time;
    
    public abstract int getStatusCode();
    
    public abstract boolean isSuccessfull();
    
    public abstract String getFullLink();
    
    public abstract String getShortLink();
    
    public abstract String getDate();
    
    public abstract String getTitle();
    
    public long getTime(){
        return time;
    }
    
    public <T> T parseResponse(String Json, Class<T> t){
        Gson gs = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls().create();
        T res = null;
        if(t.isAssignableFrom(Data.class)){
            res = gs.fromJson(Json, t);
        }
        return res;
    }
    
    public void setTime(long time){
        this.time = time;
    }
    
    public abstract static class Data{
        
        private int status;
        private String fullLink;
        private String shortLink;
        private String date;
        private String title;
    
        protected Data(int status, String fullLink, String shortLink, String date, String title){
            this.status = status;
            this.fullLink = fullLink;
            this.shortLink = shortLink;
            this.date = date;
            this.title = title;
        }
    
    
        public int getStatus(){
            return status;
        }
        
        public String getFullLink(){
            return fullLink;
        }
        
        public String getShortLink(){
            return shortLink;
        }
        
        public String getDate(){
            return date;
        }
        
        public String getTitle(){
            return title;
        }
        
        public abstract boolean isSuccessfull();
    }
    
}
