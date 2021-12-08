package com.example.notasapp;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notifica extends AppCompatActivity {

    private AlarmManager alarmManager;
    private static final String CHANNEL_ID = "RECORDATORIOS-TAREAS";
    Button btnNoti,btnAla;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.notificaciones );
        Intent intentPar = getIntent();
        int idTarea = intentPar.getIntExtra( "idTarea",1 );
        Log.d("IDTAREA","Id del Intent"+idTarea);

        NotaReciverAlarm.createNotificationChannel( getApplicationContext(),null );

        btnNoti = findViewById( R.id.btnNot );
        btnNoti.setOnClickListener( view -> {
            this.mostrarNotificacion( getApplicationContext(),null );
        } );

        btnAla = findViewById( R.id.btnAlarm );
        btnAla.setOnClickListener( view -> {
            programarAlarma();
        } );

    }

    void programarAlarma(){
        Toast.makeText( this,"Se programo alarma de 60 s",Toast.LENGTH_LONG ).show();
        alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE );

        Intent intent = new Intent(getApplicationContext(),NotaReciverAlarm.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast( getApplicationContext(),0,intent,0 );
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime()+10*1000,alarmIntent );
    }

    private void mostrarNotificacion(Context context,Intent intent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder( context,CHANNEL_ID )
                .setSmallIcon( R.mipmap.ic_launcher )
                .setContentTitle( "Titulo recordatorio" )
                .setContentText( "Tienes una tarea pendiente" )
                .setPriority( NotificationCompat.PRIORITY_DEFAULT );

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from( context );

        notificationManager.notify(1001,builder.build());
    }

    public void createNotificationChannel(Context ctx,Intent intent){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
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
