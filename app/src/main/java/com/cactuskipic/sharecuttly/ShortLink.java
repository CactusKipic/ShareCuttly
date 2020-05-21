package com.cactuskipic.sharecuttly;

import android.util.Patterns;
import android.widget.Toast;

import com.cactuskipic.sharecuttly.cuttlyapi.APIRequest;
import com.cactuskipic.sharecuttly.cuttlyapi.ContainerCuttlyServiceActivity;
import com.cactuskipic.sharecuttly.cuttlyapi.Request;

public class ShortLink{
    
    public static boolean ShortLink(ContainerCuttlyServiceActivity container, String url){
        return ShortLink(container, url, "");
    }
    
    public static boolean ShortLink(ContainerCuttlyServiceActivity container, String url, String name){
        Request request = new Request(MainActivity.tokenID);
        
        if(!((url.length()>7 && url.substring(0,7).equalsIgnoreCase("http://"))
                || (url.length()>8 && url.substring(0,8).equalsIgnoreCase("https://"))))
            url = "http://" +url;
        if(!Patterns.WEB_URL.matcher(url).matches()){
            Toast.makeText(container.getContext(),"Given URL is not a valid URL", Toast.LENGTH_SHORT).show();
            return false;
        }
        request.addArgument("short", url);
        if(name != "")
            request.addArgument("name",name);
        
        APIRequest apiRequest = new APIRequest(container);
        apiRequest.execute(request);
        
        Toast.makeText(container.getContext(),"Shorting URL", Toast.LENGTH_SHORT).show();
        return true;
    }
    
}
