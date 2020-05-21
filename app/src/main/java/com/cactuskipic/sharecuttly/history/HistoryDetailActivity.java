package com.cactuskipic.sharecuttly.history;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cactuskipic.sharecuttly.CuttlyActivity;
import com.cactuskipic.sharecuttly.MainActivity;
import com.cactuskipic.sharecuttly.R;
import com.cactuskipic.sharecuttly.cuttlyapi.Response;
import com.cactuskipic.sharecuttly.cuttlyapi.ResponseAna;
import com.cactuskipic.sharecuttly.cuttlyapi.ResponseURL;

import java.io.File;

public class HistoryDetailActivity extends CuttlyActivity{
    
    private ResponseURL responseURL;
    private String nameres;
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historydetail);
        
        findViewById(R.id.confirmationView).setVisibility(View.GONE);
        Object o = getIntent().getExtras().get("Res");
        if(o instanceof String){
            nameres = (String) o;
            responseURL = RetrieveResponse.getResponseURL(this, nameres);
        }
        if(responseURL != null){
            ((TextView) findViewById(R.id.text_detailTitle)).setText(responseURL.getTitle());
            ((TextView) findViewById(R.id.text_detailLongLink)).setText(responseURL.getFullLink());
            ((TextView) findViewById(R.id.text_detailShortLink)).setText(responseURL.getShortLink());
            ((TextView) findViewById(R.id.text_detailDate)).setText(responseURL.getDate());
        }
        
    }
    
    public void onClick(View v){
        switch(v.getId()){
            case R.id.Button_delete:
                findViewById(R.id.confirmationView).setVisibility(View.VISIBLE);
                break;
            case R.id.Button_No:
                findViewById(R.id.confirmationView).setVisibility(View.GONE);
                break;
            case R.id.imageOverlay:
                findViewById(R.id.confirmationView).setVisibility(View.GONE);
                break;
            case R.id.Button_return:
                finish();
                break;
            case R.id.Button_Yes:
                findViewById(R.id.confirmationView).setVisibility(View.GONE);
                File file = new File(getDir(getApplicationInfo().processName, MODE_PRIVATE)+ MainActivity.saveLocation+nameres);
                if(file.delete())
                    Toast.makeText(this, "Successfully deleted item", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this, "Failed to delete item", Toast.LENGTH_LONG).show();
                finish();
                break;
            case R.id.Button_stats:
                RetrieveResponse.getResponseAna(this,responseURL);
                ((Button)findViewById(R.id.Button_stats)).setEnabled(false);
                break;
        }
    }
    
    @Override
    public void GetPostExecute(Response response){
        if(response.isSuccessfull()){
            if(response instanceof ResponseAna){
                ResponseAna responseAna = (ResponseAna) response;
                ((Button)findViewById(R.id.Button_stats)).setVisibility(View.GONE);
                
                ((TextView) findViewById(R.id.text_clicks)).setText(ClicksToString(responseAna));
                
                if(responseAna.getGeolocalisations()!=null){
                    ((TextView) findViewById(R.id.text_bro)).setText(responseAna.getGeolocalisations().toString());
                }else
                Log.i(MainActivity.MARK, "No geo.");
                if(responseAna.getSystems()!=null){
                    ((TextView) findViewById(R.id.text_sys)).setText(responseAna.getSystems().toString());
                }else
                Log.i(MainActivity.MARK, "No sys.");
                if(responseAna.getDevices()!=null){
                    ((TextView) findViewById(R.id.text_dev)).setText(responseAna.getDevices().toString());
                }else
                Log.i(MainActivity.MARK, "No dev.");
                if(responseAna.getBrowsers()!=null){
                    ((TextView) findViewById(R.id.text_geo)).setText(responseAna.getBrowsers().toString());
                }else
                Log.i(MainActivity.MARK, "No bro.");
                
            }
        }else{
            switch(response.getStatusCode()){
                case 0:
                    Toast.makeText(this, "This shortened link doesn't exist", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(this, "Invalid API key", Toast.LENGTH_SHORT).show();
                    break;
            }
            ((Button)findViewById(R.id.Button_stats)).setEnabled(true);
        }
        
    }
    
    public static String ClicksToString(ResponseAna responseAna){
        StringBuilder sb = new StringBuilder();
        sb.append("Total clicks: ");
        sb.append(responseAna.getClicks());
        if(responseAna.getFacebook()>0){
            sb.append("\nFacebook: ");
            sb.append(responseAna.getFacebook());
        }
        if(responseAna.getTwitter()>0){
            sb.append("\nTwitter: ");
            sb.append(responseAna.getTwitter());
        }
        if(responseAna.getInstagram()>0){
            sb.append("\nInstagram: ");
            sb.append(responseAna.getInstagram());
        }
        if(responseAna.getLinkedin()>0){
            sb.append("\nLinkedIn: ");
            sb.append(responseAna.getLinkedin());
        }
        if(responseAna.getPinterest()>0){
            sb.append("\nPinterest: ");
            sb.append(responseAna.getPinterest());
        }
        if(responseAna.getRest()>0){
            sb.append("\nRest: ");
            sb.append(responseAna.getRest());
        }
        
        return sb.toString();
    }
}
