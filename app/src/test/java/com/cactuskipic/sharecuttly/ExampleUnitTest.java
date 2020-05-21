package com.cactuskipic.sharecuttly;

import com.cactuskipic.sharecuttly.cuttlyapi.AnaDeserialiser;
import com.cactuskipic.sharecuttly.cuttlyapi.ResponseAna;
import com.cactuskipic.sharecuttly.cuttlyapi.ResponseURL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest{
    @Test
    public void addition_isCorrect(){
        
        String json = "{\"stats\":{\"status\":1,\"clicks\":\"1\",\"date\":\"21.05.2020\",\"title\":\"ESIEA - Ecole d&amp;#039;ing&eacute;nieurs en informatique | Paris - Laval\",\"fullLink\":\"https:\\/\\/www.esiea.fr\",\"shortLink\":\"https:\\/\\/cutt.ly\\/ESIEA\",\"facebook\":0,\"twitter\":0,\"pinterest\":0,\"instagram\":0,\"googlePlus\":0,\"linkedin\":0,\"rest\":1,\"devices\":{\"dev\":{\"0\":{\"tag\":\"Desktop\",\"clicks\":\"1\"}},\"sys\":{\"0\":{\"tag\":\"Windows 8.1\",\"clicks\":\"1\"}},\"bro\":{\"0\":{\"tag\":\"Chrome 81.0\",\"clicks\":\"1\"}}},\"refs\":{}}}";
        String json2 = "{\"url\":{\"status\":7,\"fullLink\":\"https:\\/\\/roll20.net\\/\",\"date\":\"16.05.2020\",\"shortLink\":\"https:\\/\\/cutt.ly\\/YyElqwi\",\"title\":\"roll20.net\"}}";
        
        Gson gson = new GsonBuilder().registerTypeAdapter(ResponseAna.class, new AnaDeserialiser()).create();
        Gson gson2 = new GsonBuilder().create();
    
        ResponseAna dataAna = gson.fromJson(json, ResponseAna.class);
        ResponseURL resURL = gson.fromJson(json2, ResponseURL.class);
        
        System.out.println(resURL.getFullLink());
        System.out.println(resURL.getShortLink());
        System.out.println(resURL.getStatusCode());
        
        System.out.println(dataAna.getDate());
        System.out.println(dataAna.getFullLink());
        System.out.println(dataAna.getShortLink());
        System.out.println(dataAna.getStatusCode());
        System.out.println(dataAna.getTitle());
        System.out.println(dataAna.getBrowsers());
        System.out.println(dataAna.getDevices());
        System.out.println(dataAna.getGeolocalisations());
        System.out.println(dataAna.getSystems());
        
        assertEquals(1, dataAna.getStatusCode());
    }
}