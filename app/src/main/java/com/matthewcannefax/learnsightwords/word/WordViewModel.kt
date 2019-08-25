package com.matthewcannefax.learnsightwords.word

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.matthewcannefax.learnsightwords.speech.SpeechHelper

class WordViewModel(app: android.app.Application) : AndroidViewModel(app) {
    val currentSightWord = MutableLiveData<Word>()
    var wordList = arrayListOf<Word>()
    var currentIndex = 0
    val context = app

    init {
        wordList= WordHelper.getWordList()
        currentSightWord.value = wordList.get(0)
    }

    fun getSpeech(context: Context){
        SpeechHelper.getSpeechInput(context)
    }

    fun nextWord(){
        if(currentIndex == wordList.size - 1){
            currentIndex = 0
        }else{
            currentIndex++
        }

        currentSightWord.value = wordList.get(currentIndex)
    }


}