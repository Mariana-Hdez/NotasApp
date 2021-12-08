package com.example.notasapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotaReciverAlarm extends BroadcastReceiver {
    private static final String CHANNEL_ID = "RECORDATORIOS-TAREAS";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("DIF-ALARMA","Recuerdame");
        mostrarNotificacion(context,intent);
        Log.d( "DIF-ALARMA","NOTIFICANCION LANZADA" );
    }

    private void mostrarNotificacion(Context context, Intent intent) {

        Intent intentTap = new Intent(context,Notifica.class);
        intentTap.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP );
        intentTap.putExtra("idTarea",1001);

        PendingIntent pendingIntent = PendingIntent.getActivity( context,0,
                intentTap,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder( context,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle( "Titulo Recordatorio" )
                .setContentText( "Tiene una tarea pendiente" )
                .setPriority( NotificationCompat.PRIORITY_DEFAULT )
                .setContentIntent( pendingIntent )
                .setAutoCancel( true );

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from( context );
        notificationManager.notify(1001,builder.build());
    }

    public static void createNotificationChannel(Context ctx, Intent intent){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = ctx.getString( R.string.channel_name );
            String description = ctx.getString( R.string.channel_description );
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel( CHANNEL_ID,name,importance );
            channel.setDescription( description );
            NotificationManager notificationManager = ctx.getSystemService( NotificationManager.class );
            notificationManager.createNotificationChannel( channel );

        }
    }
}
