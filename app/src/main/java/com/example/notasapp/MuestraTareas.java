package com.example.notasapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notasapp.datos.Nota;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MuestraTareas extends AppCompatActivity {

    private NotaViewModel notaViewModel;
    public static final int NEW_NOTA_ACTIVITY_REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        RecyclerView recyclerView = findViewById( R.id.recyclerviewnotas );
        final NotaListAdapter adapter =
                new NotaListAdapter( new NotaListAdapter.NotaDiff()
                    ,view -> {
                    String notaCliclked =(view.getTag().toString());
                    Log.d("CLICKNOTA", notaCliclked);

                    Intent intent = new Intent(MuestraTareas.this,MostrarDetalles.class);
                    intent.putExtra( "IdNota",notaCliclked );
                    startActivity( intent );
                });
        recyclerView.setAdapter( adapter );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        notaViewModel = new ViewModelProvider( this ).get( NotaViewModel.class );

        notaViewModel.getAllTareas().observe( this,tareas -> {
            adapter.submitList( tareas );
        } );

        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MuestraTareas.this, NewNotaActivity.class);
            startActivityForResult( intent, NEW_NOTA_ACTIVITY_REQUEST_CODE );
        } );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult( requestCode,resultCode ,data);

        if (requestCode==NEW_NOTA_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK){

            String par = data.getStringExtra( NewNotaActivity.EXTRA_REPLY );
            String[] partes = par.split( "///" );
            boolean a=false,b=false;
            if(partes[4].equals( "true" )){
                a=true;
                if(partes[5].equals( "true" )){
                    b=true;
                }
            }

            Nota nota1 = new Nota(partes[0],partes[1],partes[2],partes[3],a,b);
            notaViewModel.insert( nota1 );

        }else{
            Toast.makeText(getApplicationContext(),R.string.empty,Toast.LENGTH_LONG ).show();
        }
    }
}
