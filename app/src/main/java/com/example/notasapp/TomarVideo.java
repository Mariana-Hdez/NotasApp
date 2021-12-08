package com.example.notasapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class TomarVideo extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fragment_dashboard );

        if (ContextCompat.checkSelfPermission( TomarVideo.this, Manifest.permission.WRITE_EXTERNAL_STORAGE )
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission( TomarVideo.this, Manifest.permission.CAMERA )
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions( TomarVideo.this, new String[]{Manifest.permission.
                    WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},1000 );
        }

        final Button btnCaptura = findViewById( R.id.btnVideo );
        btnCaptura.setOnClickListener( view -> {
            TomarVideo( view );
        } );

    }

    static final int REQUEST_VIDEO_CAPTURE = 1;

    public void TomarVideo(View view){
       Intent takeVideoIntent = new Intent( MediaStore.ACTION_VIDEO_CAPTURE);
       if (takeVideoIntent.resolveActivity( getPackageManager()) != null){
           startActivityForResult( takeVideoIntent,REQUEST_VIDEO_CAPTURE );
       }
    }

}
