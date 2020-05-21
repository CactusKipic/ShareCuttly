package com.cactuskipic.sharecuttly;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cactuskipic.sharecuttly.cuttlyapi.Response;
import com.cactuskipic.sharecuttly.cuttlyapi.ResponseURL;
import com.cactuskipic.sharecuttly.history.HistoryDetailActivity;
import com.cactuskipic.sharecuttly.history.SaveResponse;
import com.cactuskipic.sharecuttly.utils.RowHistoryAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends CuttlyActivity{
    
    public static final String MARK = "ShareCuttly";
    public static final String tokenID = "1a6ad441e9ecd8a1ad8599e579c0c13ee097f";
    public static final String saveLocation =  "/ShortHistory/";
    
    private RecyclerView recyclerView;
    private RowHistoryAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerHistory);
        recyclerView.setHasFixedSize(true);
        
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        
        /*if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }*/
        
    }
    
    public void onClick(View v){
        Log.i(MARK, "MainActivity Clicked");
        
        switch(v.getId()){
            case R.id.Button_short:
                ShortLink.ShortLink(this, ((TextView) findViewById(R.id.text_link)).getText().toString(),
                        ((TextView) findViewById(R.id.text_link_name)).getText().toString());
                break;
            case R.id.Button_paste:
                ShortLink.ShortLink(this, ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).getPrimaryClip().getItemAt(0).getText().toString(),
                        ((TextView) findViewById(R.id.text_link_name)).getText().toString());
                break;
            case R.id.Button_Params:
                Toast.makeText(this, "Oh oui mon mignon, touche moi encore <3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Button_Menu:
                findViewById(R.id.sidemenulayout).setVisibility(View.VISIBLE);
                break;
            case R.id.container:
                findViewById(R.id.sidemenulayout).setVisibility(View.GONE);
                break;
            case R.id.ShortLink:
                findViewById(R.id.short_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.history_layout).setVisibility(View.GONE);
                findViewById(R.id.sidemenulayout).setVisibility(View.GONE);
                break;
            case R.id.History:
                findViewById(R.id.history_layout).setVisibility(View.VISIBLE);
                findViewById(R.id.short_layout).setVisibility(View.GONE);
                findViewById(R.id.sidemenulayout).setVisibility(View.GONE);
                
                mAdapter = new RowHistoryAdapter(this, getHistoryList());
                recyclerView.setAdapter(mAdapter);
                break;
            case R.id.row_history:
                Intent intent = new Intent(this, HistoryDetailActivity.class);
                intent.putExtra("Res", (String) v.getTag());
                Toast.makeText(this,"Tag: "+(String) v.getTag(),Toast.LENGTH_LONG).show();
                startActivity(intent);
                break;
        }
        
    }
    
    private List<String> getHistoryList(){
        File file = new File(getDir(getApplicationInfo().processName, MODE_PRIVATE)+saveLocation);
        return Arrays.asList(file.list());
    }
    
    @Override
    public void GetPostExecute(Response response){
        
        if(response.isSuccessfull()){
            TextView textLink = (TextView) findViewById(R.id.text_link);
    
            textLink.setText(response.getShortLink());
            ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(
                    ClipData.newPlainText("Shortened link with ShareCuttly", response.getShortLink()));
    
            Toast.makeText(this, "Shortened link copied in clipboard", Toast.LENGTH_LONG).show();
    
            SharedPreferences pref = getSharedPreferences(this.getPackageName(), this.MODE_PRIVATE);
            if(pref.getBoolean("saveOnManualShort", true)){
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
