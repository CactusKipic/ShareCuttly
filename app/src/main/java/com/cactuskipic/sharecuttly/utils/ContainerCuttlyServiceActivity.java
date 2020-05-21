package com.cactuskipic.sharecuttly.utils;

import android.content.Context;

import com.cactuskipic.sharecuttly.CuttlyActivity;
import com.cactuskipic.sharecuttly.cuttlyapi.Response;
import com.cactuskipic.sharecuttly.share.CuttlyService;

public class ContainerCuttlyServiceActivity{
    
    // C'était une bonne idée, mais finalement inutile (╯°□°）╯︵ ┻━┻
    // Mais bon, on laisse quand même, ça pourrait être utile
    
    private CuttlyService cuttlyService = null;
    private CuttlyActivity cuttlyActivity = null;
    
    public ContainerCuttlyServiceActivity(CuttlyActivity activity){
        cuttlyActivity = activity;
    }
    
    public ContainerCuttlyServiceActivity(CuttlyService service){
        cuttlyService = service;
    }
    
    public Context getContext(){
        return (cuttlyActivity == null) ? cuttlyService.getApplicationContext() : cuttlyActivity.getApplicationContext();
    }
    
    public void GetPostExecute(Response response){
        if(cuttlyService != null)
            cuttlyService.GetPostExecute(response);
        else if(cuttlyActivity != null)
            cuttlyActivity.GetPostExecute(response);
    }
    
}
