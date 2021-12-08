package com.example.notasapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.notasapp.datos.Nota;

public class NotaListAdapter extends ListAdapter<Nota,NotaViewHolder> {

    private AdapterView.OnClickListener onItemClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onItemClickListener = onClickListener;
    }

    public NotaListAdapter(@NonNull DiffUtil.ItemCallback<Nota> diffCallback,
                           View.OnClickListener onItemClickListener) {
        super( diffCallback );
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public NotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return NotaViewHolder.create( parent,this.onItemClickListener );
    }

    @Override
    public void onBindViewHolder(NotaViewHolder holder, int position) {
        Nota current = getItem( position );
        String con = current.nid+"-"+current.titulo;
        holder.bind( con,current );
    }

    static class NotaDiff extends DiffUtil.ItemCallback<Nota>{

        @Override
        public boolean areItemsTheSame(@NonNull Nota oldItem, @NonNull Nota newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Nota oldItem, @NonNull Nota newItem) {
            return oldItem.titulo.equals( newItem.titulo );
        }
    }

}
