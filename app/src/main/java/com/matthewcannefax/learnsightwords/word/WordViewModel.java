package com.matthewcannefax.learnsightwords.word;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.matthewcannefax.learnsightwords.room.WordRepo;
import com.matthewcannefax.learnsightwords.speech.SpeechHelper;
import com.matthewcannefax.learnsightwords.word.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    //reference the database repository
    private WordRepo mRepo;

    //mutable live object to watch which word is the current word
    //the text view will change when the currentSightWord changes
    public MutableLiveData<Word> currentSightWord;

    //just a typical list from the database
    //used to manage the currentSightWord
    private List<Word> wordList;

    //also used to manage the currentSightWord
    private int currentIndex;

    //application context
    private Context context;

    //not really using this currently
    //might try to use in the future instead of wordList
    public LiveData<List<Word>> mAllWords;

    //constructor
    public WordViewModel(Application application){
        super(application);
        context = application;
        mRepo = new WordRepo(application);
        mAllWords = mRepo.getAllWords();
        wordList = mRepo.getWordList();
        currentSightWord = new MutableLiveData<>();
        setCurrentSightWord();
    }

    //method that gets a boolean to check if th user's speech was correct
    //if the speech is correct, the nextWord()method is called
    public void checkSpeechIsCorrect(boolean isCorrect){
        if (isCorrect){
            nextWord();
        }
    }

    public void setWordList(List<Word>  words){
        wordList = words;
    }

    //does what it says
    private void setCurrentSightWord(){
        currentSightWord.setValue(wordList.get(currentIndex));
    }

    public void getSpeech(){
        SpeechHelper.Companion.getSpeechInput(context);
    }

    //increments the currentIndex and changes the currentWord MutableLive object
    public void nextWord(){
        if(currentIndex == wordList.size() - 1){
            currentIndex = 0;
        }else {
            currentIndex++;
        }

        setCurrentSightWord();
    }

}
