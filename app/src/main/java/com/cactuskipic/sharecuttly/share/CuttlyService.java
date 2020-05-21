package com.cactuskipic.sharecuttly.share;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.cactuskipic.sharecuttly.cuttlyapi.Response;

import androidx.annotation.Nullable;

public abstract class CuttlyService extends Service{
    
    public abstract void GetPostExecute(Response response);
}
