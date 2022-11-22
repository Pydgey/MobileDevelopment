package com.example.prgameapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MeuDao {

    @Insert
    long inserirQuestao(Questoes questoes);

    @Query("SELECT * FROM Questoes")
    List<Questoes> pesquisarTodasQuestoes();

    @Query("DELETE FROM Questoes")
    void apagarTabela();
}
