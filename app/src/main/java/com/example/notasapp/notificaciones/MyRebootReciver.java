package com.example.notasapp.notificaciones;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class MyRebootReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context,RebootServiceClass.class);
        serviceIntent.putExtra( "caller","RebootServicer" );
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            context.startForegroundService( serviceIntent );
        }else {
            context.startService( serviceIntent );
        }
    }
}
