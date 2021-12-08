package com.example.notasapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewNotaActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private EditText mEditTitulo,mEditDes,mEditFech,mEditHora;
    private CheckBox esTar,esTer;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_nota );
        mEditTitulo = findViewById( R.id.edit_titulo );
        mEditDes = findViewById( R.id.edit_desc );
        mEditFech = findViewById( R.id.edit_fecha );
        mEditHora = findViewById( R.id.edit_hora );
        esTar = findViewById( R.id.es_tarea );
        esTer = findViewById( R.id.es_terminada );

        final Button button = findViewById( R.id.btn_save );
        button.setOnClickListener( view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty( mEditTitulo.getText())|
                    TextUtils.isEmpty( mEditDes.getText())){
                setResult( RESULT_CANCELED,replyIntent );
            }else{
                boolean ta,te;
                String fech="",hr="";
                fech = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format( new Date() );
                hr = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format( new Date() );
                Toast.makeText( this,fech,Toast.LENGTH_LONG ).show();
                Toast.makeText( this,hr,Toast.LENGTH_LONG ).show();

                if (esTar.isChecked()){
                    ta=true;
                    if (esTer.isChecked()){
                        te=true;
                    }else{
                        te=false;
                    }

                    if(TextUtils.isEmpty( mEditFech.getText() )){
                        fech = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format( new Date() );
                    }
                    if(TextUtils.isEmpty( mEditHora.getText() )){
                        hr = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format( new Date() );
                    }

                }else{
                    ta=false;
                    te=false;
                    fech = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format( new Date() );
                    hr = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format( new Date() );
                }

                if (fech==""){
                    fech = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format( new Date() );
                }

                if (hr==""){
                    hr = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format( new Date() );
                }

                String largo = mEditTitulo.getText().toString()+"///"+ mEditDes.getText().toString()+"///"+
                        fech+"///"+hr+"///"+ta+"///"+te;
                replyIntent.putExtra( EXTRA_REPLY,largo );
                setResult( RESULT_OK,replyIntent );
            }
            finish();
        } );
    }
}
