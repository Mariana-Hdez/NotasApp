package com.example.notasapp.datos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Nota {

    @PrimaryKey(autoGenerate = true)
    public int nid;

    @ColumnInfo(name = "titulo")
    public String titulo;

    @ColumnInfo(name = "decripcion")
    public String descripcion;

    @ColumnInfo(name = "fecha")
    public String fecha;

    @ColumnInfo(name = "hora")
    public String hora;

    @ColumnInfo(name = "esTarea")
    public boolean esTarea;

    @ColumnInfo(name = "esTerminada")
    public boolean esTerminada;

    public Nota(@NonNull String titulo,@NonNull String descripcion,@NonNull String fecha,@NonNull String hora
    ,@NonNull boolean esTarea, @NonNull boolean esTerminada){
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.fecha=fecha;
        this.hora=hora;
        this.esTarea=esTarea;
        this.esTerminada=esTerminada;
    }

}
