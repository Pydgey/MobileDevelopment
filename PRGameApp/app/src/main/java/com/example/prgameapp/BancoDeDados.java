package com.example.prgameapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Questoes.class}, version = 1)
public abstract class BancoDeDados extends RoomDatabase {

    private static BancoDeDados INSTANCE;
    public abstract MeuDao meuDao();

    public static BancoDeDados getBancoDeDados(final Context context){
        if(INSTANCE == null){
            synchronized (BancoDeDados.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class,
                            "bd_questoes").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

}
