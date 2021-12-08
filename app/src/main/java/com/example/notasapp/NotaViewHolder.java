package com.example.notasapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.notasapp.datos.Nota;

class NotaViewHolder extends RecyclerView.ViewHolder {
    private final TextView notaItemView;

    private NotaViewHolder(View itemView , View.OnClickListener onItemClickListener) {
        super( itemView );
        notaItemView = itemView.findViewById( R.id.textView );
        notaItemView.setOnClickListener( onItemClickListener );
    }

    public void bind(String text, Nota current){
        notaItemView.setText( text );
        String tag = current.nid+"///"+current.titulo+"///"+current.descripcion+"///"+ current.fecha+"///"
                +current.hora+"///"+current.esTarea+"///"+current.esTerminada;
        notaItemView.setTag( tag );
    }

    static NotaViewHolder create(ViewGroup parent, AdapterView.OnClickListener onItemClickListener){
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.recyclerview_item, parent, false );
        return new NotaViewHolder( view , onItemClickListener);
    }

}
