package com.matthewcannefax.learnsightwords.word

import com.matthewcannefax.learnsightwords.sample.SampleWords

class WordHelper {
    companion object{

        private var index = 0

        public fun getWordList(): ArrayList<Word>{

            return SampleWords.getLevelOne().levelWordList
        }

        public fun getIndex(): Int{
            return index++
        }


    }
}