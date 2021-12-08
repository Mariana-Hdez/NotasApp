package com.example.notasapp.notificaciones;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.notasapp.R;

public class RebootServiceClass extends IntentService {

    /**
     * @param name
     * @deprecated
     */
    public RebootServiceClass(String name) {
        super( name );
        startForeground( 1,new Notification() );
    }

    public RebootServiceClass(){
        super("RebootServiceClass");
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String intentType = intent.getExtras().getString( "caller" );
        if (intentType==null)return;
        if (intentType.equals( "RebootReciver" )){
            SharedPreferences settings = getSharedPreferences( getString( R.string.app_name ), Context.MODE_PRIVATE );
            Utils.setAlarm( settings.getInt( "alarmID",0 ),settings.getLong( "alarmTime",0 ),this );
        }
    }
}
