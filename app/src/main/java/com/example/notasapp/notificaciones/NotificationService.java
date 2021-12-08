package com.example.notasapp.notificaciones;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.notasapp.R;

public class NotificationService extends IntentService {

    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;
    private static int NOTIFICATION_ID = 1;
    Notification notification;

    public NotificationService(String name) {
        super( name );
    }

    public NotificationService() {
        super( "SERVICE" );
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String NOTIFICATION_CHANNEL_ID= getApplicationContext().getString( R.string.app_name );
        Context context = this.getApplicationContext();
        notificationManager = (NotificationManager) context.getSystemService( Context.NOTIFICATION_SERVICE );
        Intent miIntent = new Intent(this,Notificando.class);
        Resources res = this.getResources();
        Uri soundUri = RingtoneManager.getDefaultUri( RingtoneManager.TYPE_ALARM );

        String message = "Tienes una terea pendiente";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            final int NOTIFY_ID = 0;//id de notificación
            String id = NOTIFICATION_CHANNEL_ID;//canal por default
            String title = NOTIFICATION_CHANNEL_ID;//canal default
            PendingIntent pendingIntent;
            NotificationCompat.Builder builder;
            NotificationManager notifManager = (NotificationManager) context.getSystemService( Context.NOTIFICATION_SERVICE );
            if (notifManager == null){
                notifManager = (NotificationManager) context.getSystemService( Context.NOTIFICATION_SERVICE );
            }
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel mChannel = notifManager.getNotificationChannel( id );
            if (mChannel==null){
                mChannel = new NotificationChannel( id,title,importance );
                mChannel.enableVibration( true );
                mChannel.setVibrationPattern( new long[]{100,200,300,400,500,400,300,200,400} );
                notifManager.createNotificationChannel( mChannel );
            }
            builder = new NotificationCompat.Builder( context,id );
            miIntent.setFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            pendingIntent = PendingIntent.getActivity( context, 0 , miIntent, PendingIntent.FLAG_UPDATE_CURRENT );
            builder.setContentTitle( getString( R.string.app_name )).setCategory( Notification.CATEGORY_REMINDER )
                    .setSmallIcon( R.drawable.ic_launcher_background )
                    .setContentText( message )
                    .setDefaults( Notification.DEFAULT_ALL )
                    .setAutoCancel( true )
                    .setSound( soundUri )
                    .setContentIntent( pendingIntent )
                    .setVibrate( new long[]{100,200,300,400,500,400,300,200,400} );

            Notification notification = builder.build();
            notifManager.notify(NOTIFY_ID,notification);
            startForeground( 1,notification );
        }else{
            pendingIntent = PendingIntent.getActivity( context,1,miIntent,PendingIntent.FLAG_UPDATE_CURRENT );
            notification = new NotificationCompat.Builder( this )
                    .setContentIntent( pendingIntent )
                    .setSmallIcon( R.drawable.ic_launcher_background )
                    .setSound( soundUri )
                    .setAutoCancel( true )
                    .setContentTitle( getString( R.string.app_name ))
                    .setCategory( Notification.CATEGORY_REMINDER )
                    .setContentText( message ).build();
            notificationManager.notify(NOTIFICATION_ID,notification);

        }
    }
}
