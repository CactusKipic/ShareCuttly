package com.cactuskipic.sharecuttly.cuttlyapi;

public class ResponseURL extends Response{
    
    private DataURL url;
    
    public DataURL getData(){
        return url;
    }
    
    @Override
    public int getStatusCode(){
        return url.getStatus();
    }
    
    @Override
    public boolean isSuccessfull(){
        return url.isSuccessfull();
    }
    
    @Override
    public String getFullLink(){
        return url.getFullLink();
    }
    
    @Override
    public String getShortLink(){
        return url.getShortLink();
    }
    
    @Override
    public String getDate(){
        return url.getDate();
    }
    
    @Override
    public String getTitle(){
        return url.getTitle();
    }
    
    
    public static class DataURL extends Data{
    
        protected DataURL(int status, String fullLink, String shortLink, String date, String title){
            super(status, fullLink, shortLink, date, title);
        }
    
        @Override
        public boolean isSuccessfull(){
            return getStatus() == 7;
        }
    }
}
