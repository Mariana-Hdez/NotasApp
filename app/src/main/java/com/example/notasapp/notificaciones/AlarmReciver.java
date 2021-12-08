package com.example.notasapp.notificaciones;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class AlarmReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service1 = new Intent(context,NotificationService.class);
        service1.setData( (Uri.parse("custom//" + System.currentTimeMillis())));
        ContextCompat.startForegroundService( context,service1 );
        Log.d("DIF_ALARMA","ALARM RECIVED!!!");
    }
}
