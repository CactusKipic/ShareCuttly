package com.cactuskipic.sharecuttly.share;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.cactuskipic.sharecuttly.MainActivity;
import com.cactuskipic.sharecuttly.cuttlyapi.Response;
import com.cactuskipic.sharecuttly.cuttlyapi.ResponseURL;
import com.cactuskipic.sharecuttly.history.SaveResponse;

import androidx.annotation.Nullable;

public class ShortNCopyService extends CuttlyService{
    
    // Not used, cuz you can't receive share intent on a service (╯°□°）╯︵ ┻━┻
    
    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(MainActivity.MARK, "CreateService");
        //ShortLink.ShortLink(new ContainerCuttlyServiceActivity(this), getIntent().getExtras().getString("URL"));
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i(MainActivity.MARK, "StartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        Log.i(MainActivity.MARK, "Bind binder");
        return null;
    }
    
    @Override
    public void GetPostExecute(Response response){
        Log.i(MainActivity.MARK, "SharePostExec");
        if(response.isSuccessfull()){
            
            ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(
                    ClipData.newPlainText("Shortened link with ShareCuttly", response.getShortLink()));
        
            Toast.makeText(this, "Shortened link copied in clipboard", Toast.LENGTH_LONG).show();
        
            SharedPreferences pref = getSharedPreferences(this.getPackageName(), this.MODE_PRIVATE);
            if(pref.getBoolean("saveOnShareShort", true)){
                SaveResponse.SaveResponse(this, (ResponseURL) response);
            }
        } else {
            switch(response.getStatusCode()){
                case 1:
                    Toast.makeText(this, "The given url was already a shorten url.", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(this, "The given url is not a proper url.", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(this, "The preferred link name is already taken.", Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText(this, "Invalid API key.", Toast.LENGTH_LONG).show();
                    break;
                case 5:
                    Toast.makeText(this, "The given url contains invalid characters.", Toast.LENGTH_LONG).show();
                    break;
                case 6:
                    Toast.makeText(this, "The given url is from a blocked domain.", Toast.LENGTH_LONG).show();
                    break;
                case 7:
                    Toast.makeText(this, "Unknown error on the API response.", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
