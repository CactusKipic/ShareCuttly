package com.cactuskipic.sharecuttly;

import com.cactuskipic.sharecuttly.cuttlyapi.Response;

import androidx.appcompat.app.AppCompatActivity;

public abstract class CuttlyActivity extends AppCompatActivity{
    
    public abstract void GetPostExecute(Response response);
    
}
