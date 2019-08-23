package com.matthewcannefax.learnsightwords.word

import com.matthewcannefax.learnsightwords.sample.SampleWords

class WordHelper {
    companion object{

        private var wordList = arrayListOf<Word>()
        private var index = 0

        public fun getWordList(): ArrayList<Word>{

            wordList = SampleWords.getWordList()

            return wordList
        }

        public fun getIndex(): Int{
            return index++
        }


    }
}