package com.example.notasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notasapp.datos.Nota;
import com.example.notasapp.imagenes.TomarFoto;
import com.example.notasapp.notificaciones.Notificando;
import com.example.notasapp.videos.TomarVideo;

public class MostrarDetalles extends AppCompatActivity {

    Button edita,elimina,foto,audio,video,notificacion;
    TextView texto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.muestra_nota );

        Bundle extras = getIntent().getExtras();
        String id = extras.getString( "IdNota" );
        String[] partes = id.split( "///" );
        boolean x=false,y=false;
        if(partes[5].equals( "true" )){
            x=true;
            if (partes[6].equals( "true" )){
                y=true;
            }
        }

         Nota nn = new Nota(partes[1],partes[2],partes[3],partes[4],x,y);

        String a="",b="";
        if (nn.esTarea==true){
            a="Soy tarea";
            if (nn.esTerminada==true){
                b=" y estoy terminada!";
            }else{
                b="pero no estoy terminada";
            }
        }else{
            a="Yo no soy tarea";
        }

        String mu = nn.nid+"   "+nn.titulo+"\n"+nn.descripcion+"\nFecha: "+nn.fecha
                +"\tHora: "+ nn.hora+"\n"+a+" "+b;


        texto = findViewById( R.id.txt_todo );
        texto.setText( mu );

        video = findViewById( R.id.btn_cap_vid );
        video.setOnClickListener( view -> {
            Intent i = new Intent(this, TomarVideo.class);
            startActivity( i );
        } );

        foto = findViewById( R.id.btn_cap_foto );
        foto.setOnClickListener( view -> {
            Intent i = new Intent(this, TomarFoto.class);
            startActivity( i );
        } );

        audio = findViewById( R.id.btn_ga_audio );
        audio.setOnClickListener( view -> {
            Toast.makeText( this,"Debería grabar audio pero se fresea",Toast.LENGTH_LONG ).show();
            //Intent i = new Intent(this, TomarAudio.class);
            //startActivity( i );
            //---------------------------------------------------------------------------------------------
        } );

        edita=findViewById( R.id.btnEdita );
        edita.setOnClickListener( view -> {
            //--------------------------------OPCION EDITAR-----------------------------------------------
            Toast.makeText( this,"Editando!!!",Toast.LENGTH_LONG ).show();
        } );

        elimina = findViewById( R.id.btnElimina );
        elimina.setOnClickListener( view -> {
            Toast.makeText( this,"Eliminando",Toast.LENGTH_LONG ).show();
            //-------------------------------OPCION ELIMINAR---------------------------------------------
        } );

        notificacion = findViewById( R.id.btn_notis );
        notificacion.setOnClickListener( view -> {
            Intent i = new Intent(MostrarDetalles.this, Notificando.class);
            startActivity( i );
        } );

    }

}
