package com.example.notasapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notasapp.datos.Nota;

import java.util.List;

public class NotaViewModel extends AndroidViewModel {

    private NotaRepository mRepository;
    private final LiveData<List<Nota>> mAllNotas;
    private final LiveData<List<Nota>> mAllTareas;

    public NotaViewModel(Application application) {
        super( application );
        mRepository = new NotaRepository( application );
        mAllNotas = mRepository.getAllNotas();
        mAllTareas = mRepository.getAllTareas();
    }

    LiveData<List<Nota>> getAllNotas(){return mAllNotas;}
    LiveData<List<Nota>> getAllTareas(){return mAllTareas;}

    public void insert(Nota nota){mRepository.insert( nota );}
    public void update(Nota nota){mRepository.update(nota);}
}
