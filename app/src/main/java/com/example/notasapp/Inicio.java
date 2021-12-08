package com.example.notasapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Inicio extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.inicio_ventana );



        final Button btnNo = findViewById( R.id.btn_ir_notas );
        btnNo.setOnClickListener( view -> {
            Intent i = new Intent(this,Principal.class);
            startActivity( i );
        } );

        final Button btnTa = findViewById( R.id.btn_ir_tareas );
        btnTa.setOnClickListener( view -> {
            Intent i = new Intent(this,MuestraTareas.class);
            startActivity( i );
        } );

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.menu_buscar){
            Toast.makeText( this,"Buscando...",Toast.LENGTH_LONG ).show();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}
