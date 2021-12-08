package com.example.notasapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class TomarFoto extends AppCompatActivity {

    Button btn;
    ImageView imagen;
    String rutaImg;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.tomar_foto );
        btn=findViewById( R.id.btnFoto );
        imagen=findViewById( R.id.muestra_foto );

        btn.setOnClickListener( view -> {
            abreCamara();
        } );
    }

    private void abreCamara(){
        Intent i = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        if (i.resolveActivity( getPackageManager())!=null){

            File imagenArchivo = null;
            try {
                imagenArchivo = creaImg();
            }catch (IOException ex){
                Log.e("Error",ex.toString());
            }

            if (imagenArchivo != null){
                Uri fotoUri = FileProvider.getUriForFile(this,"com.example.notasapp.fileprovider",
                        imagenArchivo);
                i.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                startActivityForResult( i,1 );
            }
        }
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap img = BitmapFactory.decodeFile(rutaImg);
            imagen.setImageBitmap( img );
        }
    }

    private File creaImg() throws IOException {
        String nombreImg = "appNotas_";
        File dirctorio = getExternalFilesDir( Environment.DIRECTORY_PICTURES );
        File imagn = File.createTempFile( nombreImg,".jgp",dirctorio );
        rutaImg = imagn.getAbsolutePath();
        return  imagn;
    }
}
