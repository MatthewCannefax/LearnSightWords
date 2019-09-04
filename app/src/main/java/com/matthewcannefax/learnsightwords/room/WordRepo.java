package com.matthewcannefax.learnsightwords.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.matthewcannefax.learnsightwords.word.Word;

import java.util.ArrayList;
import java.util.List;

public class WordRepo {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWordsLive;
    private List<Word> mWordList;


    public WordRepo(Application application){
        WordDatabase db = WordDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mWordList = mWordDao.getAllWords();
        mAllWordsLive = mWordDao.getAllWordsLive();
    }

    //get a list of words that isn't a livedata object
    public List<Word> getWordList(){return mWordList;}

    //get a list of words that IS a livedata object
    public LiveData<List<Word>> getAllWords() {return mAllWordsLive;}

    //get a list of words of a specific level
    public LiveData<List<Word>> getLevelWords(int wordLevel){
        return mWordDao.getLevelWords(wordLevel);
    }

    public void replaceDummyWord(String realWord){
        mWordDao.replaceDummyWord(realWord, DatabaseConstantsKt.LEVEL_FIRST_WORD_DUMMY);
    }

    //get an array of integers that represents the different levels for the words
    public List<Integer> getAllLevels() { return mWordDao.getLevelsArray(); }

    //get a livedata list of integers that represents the different levels for the words
    public LiveData<List<Integer>> getAllLevelsLive(){return mWordDao.getLevelsLive();}

    //insert a new word into the database
    public void insert(Word word){new insertAsyncTask(mWordDao).execute(word);}

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao){mAsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.Insert(words[0]);
            return null;
        }
    }

}
