package com.example.notasapp.videos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.notasapp.R;

import java.io.File;
import java.io.IOException;

public class TomarVideo extends AppCompatActivity {

    Button btn,btn2;
    VideoView video;
    String rutaVid;
    Uri urivideo;

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

        btn = findViewById( R.id.btnVideo );
        btn.setOnClickListener( view -> {
            abreCamara();
            //TomarVideo( view );
        } );

        btn2=findViewById( R.id.btnVideoGaleria );
        btn2.setOnClickListener( view -> {
            Intent galery = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
            startActivityForResult( galery,2 );
        } );
    }

    private void abreCamara(){
        Intent i = new Intent( MediaStore.ACTION_VIDEO_CAPTURE );
        if (i.resolveActivity( getPackageManager())!=null){

            File imagenArchivo = null;
            try {
                imagenArchivo = creaVid();
            }catch (IOException ex){
                Log.e("Error",ex.toString());
            }

            if (imagenArchivo != null){
                urivideo = FileProvider.getUriForFile(this,"com.example.notasapp.fileprovider",
                        imagenArchivo);
                i.putExtra(MediaStore.EXTRA_OUTPUT,urivideo);
                startActivityForResult( i,1 );
            }
        }
    }

    private File creaVid() throws IOException {
        String nombreVid = "appNotas_";
        File dirctorio = getExternalFilesDir( Environment.DIRECTORY_MOVIES );
        File vid = File.createTempFile( nombreVid,".mp3",dirctorio );
        rutaVid = vid.getAbsolutePath();
        return  vid;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //video.setVideoURI( urivideo );
            //video.start();
        }else if(requestCode == 2 && resultCode == RESULT_OK){
            urivideo = data.getData();
            //video.setVideoURI( urivideo );
            //video.start();
        }
    }

    static final int REQUEST_VIDEO_CAPTURE = 1;

  /*  public void TomarVideo(View view){
       Intent takeVideoIntent = new Intent( MediaStore.ACTION_VIDEO_CAPTURE);
       if (takeVideoIntent.resolveActivity( getPackageManager()) != null){
           startActivityForResult( takeVideoIntent,REQUEST_VIDEO_CAPTURE );
       }
    }*/

}
