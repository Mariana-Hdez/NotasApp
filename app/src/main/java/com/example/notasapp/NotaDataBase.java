package com.example.notasapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.notasapp.datos.Nota;
import com.example.notasapp.datos.NotaDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database( entities = {Nota.class}, version = 1, exportSchema = false)
public abstract class NotaDataBase extends RoomDatabase{

    public abstract NotaDao notaDaao();

    private static volatile NotaDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool( NUMBER_OF_THREADS );

    static NotaDataBase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (NotaDataBase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotaDataBase.class, "nota_database")
                            .addCallback( sRoomDatabaseCallback )
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate( db );

            databaseWriteExecutor.execute( ()->{
                NotaDao dao = INSTANCE.notaDaao();
                dao.deleteAll();

                Nota nota = new Nota("jesus","Agregando la primera nota",
                        "2021-11-28","10:32",false,false);
                dao.insertAll( nota );
                nota = new Nota("gabriel","Agregando la segunda nota",
                        "2021-11-28","10:32",false,false);
                dao.insertAll( nota );
                nota = new Nota("wiarco","Agregando la tercera nota",
                        "2021-11-28","10:32",false,false);
                dao.insertAll( nota );

            } );
        }
    };

}
