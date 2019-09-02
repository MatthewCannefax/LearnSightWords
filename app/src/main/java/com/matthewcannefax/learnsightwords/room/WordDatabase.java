package com.matthewcannefax.learnsightwords.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.matthewcannefax.learnsightwords.sample.SampleWords;
import com.matthewcannefax.learnsightwords.word.Word;

import static com.matthewcannefax.learnsightwords.room.DatabaseConstantsKt.WORD_DATABASE_VERSION;
import static com.matthewcannefax.learnsightwords.room.DatabaseConstantsKt.WORD_DATABSE_NAME;

@Database(entities = {Word.class}, version = WORD_DATABASE_VERSION, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static WordDatabase INSTANCE;

    static WordDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDatabase.class, WORD_DATABSE_NAME)
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }

            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private final WordDao mDao;

        PopulateDbAsync(WordDatabase db){mDao = db.wordDao();}

        @Override
        protected Void doInBackground(Void... voids) {

            mDao.deleteAll();//this will need to change, don't want to completely clear out the db everytime

            SampleWords sampleWords = new SampleWords();
            for (Word word :
                    sampleWords.getWordListNonStatic()) {
                mDao.Insert(word);
            }

            return null;
        }
    }
}
