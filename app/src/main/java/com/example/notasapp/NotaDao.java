package com.example.notasapp;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;
import java.util.List;

@Dao
public interface NotaDao {

    @Query( "select * from nota where nid in (:notaIds)" )
    LiveData<List<Nota>> loadAllByTds(int[] notaIds);

    @Query( "select * from nota where esTarea = 1 order by fecha asc" )
    LiveData<List<Nota>> getTareas();

    @Query( "select * from nota where esTarea = 0 order by fecha desc" )
    LiveData<List<Nota>> getNotas();

    @Insert
    void insertAll(Nota... notas);

    @Delete
    void delete(Nota nota);

    @Update
    void update(Nota nota);

    @Query( "delete from nota" )
    void deleteAll();

}
