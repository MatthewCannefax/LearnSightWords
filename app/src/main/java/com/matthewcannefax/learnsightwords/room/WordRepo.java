package com.matthewcannefax.learnsightwords.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.matthewcannefax.learnsightwords.word.Word;

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

    public List<Word> getWordList(){return mWordList;}

    public LiveData<List<Word>> getAllWords() {return mAllWordsLive;}

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
