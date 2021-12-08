package com.example.notasapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notasapp.datos.Nota;
import com.example.notasapp.datos.NotaDao;

import java.util.List;

class NotaRepository {

    private NotaDao mNotaDao;
    private LiveData<List<Nota>> mAllNotas;
    private LiveData<List<Nota>> mAllTareas;

    NotaRepository(Application application){
        NotaDataBase db = NotaDataBase.getDatabase( application );
        mNotaDao = db.notaDaao();
        mAllNotas = mNotaDao.getNotas();
        mAllTareas = mNotaDao.getTareas();
    }

    LiveData<List<Nota>> getAllNotas(){
        return mAllNotas;
    }
    LiveData<List<Nota>> getAllTareas(){
        return mAllTareas;
    }

    void insert(Nota nota){
        NotaDataBase.databaseWriteExecutor.execute( ()->{
            mNotaDao.insertAll( nota );
        } );
    }

    void update(Nota nota){
        NotaDataBase.databaseWriteExecutor.execute( ()->{
            mNotaDao.update( nota );
        } );
    }

}
