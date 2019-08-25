package com.matthewcannefax.learnsightwords.sample

import com.matthewcannefax.learnsightwords.word.Level
import com.matthewcannefax.learnsightwords.word.Word

class SampleWords {
    companion object{

        fun getLevelOne(): Level{
            var wordList = getWordList()
            var level: Level = Level("Level 1", 1, wordList)
            return level
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