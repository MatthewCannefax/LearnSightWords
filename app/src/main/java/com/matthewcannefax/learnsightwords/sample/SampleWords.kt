package com.matthewcannefax.learnsightwords.sample

import com.matthewcannefax.learnsightwords.word.SightWordLevel
import com.matthewcannefax.learnsightwords.word.Word

class SampleWords() {

    fun getWordListNonStatic(): ArrayList<Word>{
        val wordList = ArrayList<Word>()
        wordList.add(Word("the"))
//        wordList.add(Word("to"))
//        wordList.add(Word("and"))
//        wordList.add(Word("he"))
//        wordList.add(Word("a"))
//        wordList.add(Word("I"))
//        wordList.add(Word("you"))
//        wordList.add(Word("it"))
//        wordList.add(Word("of"))
//        wordList.add(Word("in"))
//        wordList.add(Word("was"))
//        wordList.add(Word("said"))
//        wordList.add(Word("his"))
//        wordList.add(Word("that"))
//        wordList.add(Word("she"))
//        wordList.add(Word("for"))
//        wordList.add(Word("on"))
//        wordList.add(Word("they"))
//        wordList.add(Word("but"))
//        wordList.add(Word("had"))
//        wordList.add(Word("at"))
//        wordList.add(Word("him"))
//        wordList.add(Word("with"))
//        wordList.add(Word("up"))
        wordList.add(Word("all"))

        return wordList
    }

    companion object{



        fun getLevelOne(): ArrayList<SightWordLevel>{
            var levelList: ArrayList<SightWordLevel> = ArrayList()
            var wordList = getWordList()
            var level: SightWordLevel = SightWordLevel()
            levelList.add(level)
            return levelList
        }

        fun getWordList(): ArrayList<Word>{
            val wordList = ArrayList<Word>()
            wordList.add(Word("the"))
            wordList.add(Word("to"))
            wordList.add(Word("and"))
            wordList.add(Word("he"))
            wordList.add(Word("a"))
            wordList.add(Word("I"))
            wordList.add(Word("you"))
            wordList.add(Word("it"))
            wordList.add(Word("of"))
            wordList.add(Word("in"))
            wordList.add(Word("was"))
            wordList.add(Word("said"))
            wordList.add(Word("his"))
            wordList.add(Word("that"))
            wordList.add(Word("she"))
            wordList.add(Word("for"))
            wordList.add(Word("on"))
            wordList.add(Word("they"))
            wordList.add(Word("but"))
            wordList.add(Word("had"))
            wordList.add(Word("at"))
            wordList.add(Word("him"))
            wordList.add(Word("with"))
            wordList.add(Word("up"))
            wordList.add(Word("all"))

            return wordList
        }
    }
}