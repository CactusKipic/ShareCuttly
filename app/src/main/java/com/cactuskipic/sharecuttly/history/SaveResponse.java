package com.cactuskipic.sharecuttly.history;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cactuskipic.sharecuttly.MainActivity;
import com.cactuskipic.sharecuttly.cuttlyapi.ResponseURL;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveResponse{
    
    public static void SaveResponse(Context context, ResponseURL responseURL){
        File file = new File(context.getDir(context.getApplicationInfo().processName, Context.MODE_PRIVATE) +
                MainActivity.saveLocation + String.valueOf(responseURL.getTime())+".json");
        if(!file.getParentFile().exists()){
            Log.i(MainActivity.MARK, "Parent don't exists !");
            file.mkdirs();
        }
        try{
            FileOutputStream out = new FileOutputStream(file);
            Gson gson = new Gson();
            
            out.write(gson.toJson(responseURL).getBytes());
            
            out.close();
            Log.i(MainActivity.MARK, "Response saved | "+file.toString());
        }catch(IOException e){
            Toast.makeText(context,"Could not save shortened link in history", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    
    }
}
