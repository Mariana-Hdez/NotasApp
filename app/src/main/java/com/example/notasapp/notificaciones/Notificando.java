package com.example.notasapp.notificaciones;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notasapp.R;

import java.util.Calendar;

public class Notificando extends AppCompatActivity {
    private TextView notificationsTime;
    private int alarmID = 1;
    private SharedPreferences settings;
    private static final String CHANNEL_ID = "RECORDATORIOS-TAREAS";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.notificaciones );

        settings = getSharedPreferences( getString( R.string.app_name ), Context.MODE_PRIVATE );
        String hora,minuto;
        hora = settings.getString( "hora","" );
        minuto = settings.getString( "minuto", "" );

        notificationsTime = (TextView) findViewById( R.id.notification_time );

        if(hora.length() > 0){
            notificationsTime.setText( hora + ":" + minuto );
        }

        findViewById( R.id.btnAlarm ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hora = mcurrentTime.get( Calendar.HOUR_OF_DAY );
                int minuto = mcurrentTime.get( Calendar.MINUTE );
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog( Notificando.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHora, int selectedMinuto) {
                            String finalHora,finalMinuto;
                            finalHora = "" + selectedHora;
                            finalMinuto = "" + selectedMinuto;
                            if (selectedHora<10)finalHora="0"+selectedHora;
                            if (selectedMinuto < 10)finalMinuto ="0"+selectedMinuto;
                            notificationsTime.setText( finalHora+":"+finalMinuto );
                            Calendar today = Calendar.getInstance();
                            today.set(Calendar.HOUR_OF_DAY, selectedHora);
                            today.set(Calendar.MINUTE,selectedMinuto);
                            today.set(Calendar.SECOND,0);

                            SharedPreferences.Editor edit = settings.edit();
                            edit.putString( "hora",finalHora );
                            edit.putString( "minuto",finalMinuto );

                            edit.putInt( "alarmID",alarmID );
                            edit.putLong( "alarmTime", today.getTimeInMillis() );

                            edit.commit();
                            Toast.makeText( Notificando.this,"Nueva notificacion a las: "
                                    + finalHora+":"+finalMinuto,Toast.LENGTH_LONG ).show();
                        Utils.setAlarm( alarmID,today.getTimeInMillis(),Notificando.this );
                    }
                },hora,minuto,true );

                mTimePicker.setTitle( "Elija la hora" );
                mTimePicker.show();
            }
        } );
    }
}
