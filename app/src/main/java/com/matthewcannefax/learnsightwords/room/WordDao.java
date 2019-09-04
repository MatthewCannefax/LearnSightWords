package com.matthewcannefax.learnsightwords.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.matthewcannefax.learnsightwords.word.Word;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void Insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table")
    LiveData<List<Word>> getAllWordsLive();

    @Query("SELECT * FROM word_table")
    List<Word> getAllWords();

    @Query("SELECT * FROM word_table WHERE level = :levelInt ")
    LiveData<List<Word>> getLevelWords(int levelInt);

    @Query("SELECT level FROM word_table GROUP BY level")
    List<Integer> getLevelsArray();

    @Query("SELECT level FROM word_table GROUP BY level")
    LiveData<List<Integer>> getLevelsLive();

    @Query("UPDATE word_table SET word = :realWord WHERE word = :dummyCode")
    void replaceDummyWord(String realWord, String dummyCode);

    //need a method to get all words with a specific Sightwordlevel

    //need a method to remove all words with a specific sightwordlevel


}
