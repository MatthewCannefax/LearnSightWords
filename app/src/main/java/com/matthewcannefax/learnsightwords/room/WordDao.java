package com.matthewcannefax.learnsightwords.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.matthewcannefax.learnsightwords.word.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void Insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * FROM word_table")
    LiveData<List<Word>> getAllWordsLive();

    @Query("SELECT * FROM word_table")
    List<Word> getAllWords();

    //need a method to get all words with a specific Sightwordlevel

    //need a method to remove all words with a specific sightwordlevel


}
