package com.cactuskipic.sharecuttly.cuttlyapi;

import java.util.HashMap;

public class ResponseAna extends Response{
    
    private DataAna ana;
    
    public ResponseAna(DataAna ana){
        super();
        this.ana = ana;
    }
    
    @Override
    public int getStatusCode(){
        return ana.getStatus();
    }
    
    @Override
    public boolean isSuccessfull(){
        return ana.isSuccessfull();
    }
    
    @Override
    public String getFullLink(){
        return ana.getFullLink();
    }
    
    @Override
    public String getShortLink(){
        return ana.getShortLink();
    }
    
    @Override
    public String getDate(){
        return ana.getDate();
    }
    
    @Override
    public String getTitle(){
        return ana.getTitle();
    }
    
    
    public String getClicks(){
        return ana.getClicks();
    }
    
    public int getFacebook(){
        return ana.getFacebook();
    }
    
    public int getTwitter(){
        return ana.getTwitter();
    }
    
    public int getPinterest(){
        return ana.getPinterest();
    }
    
    public int getInstagram(){
        return ana.getInstagram();
    }
    
    public int getGooglePlus(){
        return ana.getGooglePlus();
    }
    
    public int getLinkedin(){
        return ana.getLinkedin();
    }
    
    public int getRest(){
        return ana.getRest();
    }
    
    public HashMap<String, Integer> getGeolocalisations(){
        return ana.getGeo();
    }
    
    public HashMap<String, Integer> getDevices(){
        return ana.getDev();
    }
    
    public HashMap<String, Integer> getSystems(){
        return ana.getSys();
    }
    
    public HashMap<String, Integer> getBrowsers(){
        return ana.getBro();
    }
    
    public static class DataAna extends Data{
        
        private String clicks;
        private int facebook;
        private int twitter;
        private int pinterest;
        private int instagram;
        private int googlePlus;
        private int linkedin;
        private int rest;
        
        private HashMap<String, Integer> geo;
        private HashMap<String, Integer> dev;
        private HashMap<String, Integer> sys;
        private HashMap<String, Integer> bro;
        
        public DataAna(int status, String fullLink, String shortLink, String date, String title,
                       String clicks, int facebook, int twitter, int pinterest, int instagram, int googlePlus, int linkedin, int rest,
                       HashMap<String, Integer> geo, HashMap<String, Integer>dev, HashMap<String, Integer> sys, HashMap<String, Integer> bro){
            super(status, fullLink, shortLink, date, title);
            this.clicks = clicks;
            this.facebook = facebook;
            this.twitter = twitter;
            this.pinterest = pinterest;
            this.instagram = instagram;
            this.googlePlus = googlePlus;
            this.linkedin = linkedin;
            this.rest = rest;
            this.geo = geo;
            this.dev = dev;
            this.sys = sys;
            this.bro = bro;
        }
        
        @Override
        public boolean isSuccessfull(){
            return getStatus() == 1;
        }
    
        public HashMap<String, Integer> getGeo(){
            return geo;
        }
    
        public HashMap<String, Integer> getDev(){
            return dev;
        }
    
        public HashMap<String, Integer> getSys(){
            return sys;
        }
    
        public HashMap<String, Integer> getBro(){
            return bro;
        }
    
        public String getClicks(){
            return clicks;
        }
    
        public int getFacebook(){
            return facebook;
        }
    
        public int getTwitter(){
            return twitter;
        }
    
        public int getPinterest(){
            return pinterest;
        }
    
        public int getInstagram(){
            return instagram;
        }
    
        public int getGooglePlus(){
            return googlePlus;
        }
    
        public int getLinkedin(){
            return linkedin;
        }
    
        public int getRest(){
            return rest;
        }
    }
}
