package com.matthewcannefax.learnsightwords.word

class SightWordLevel {
    var levelName: String = ""
    var levelID: Long = -1
    var levelWordList = arrayListOf<Word>()

    //these are to hold arrays of the word IDs
    var levelWordsMastered = arrayOf<Long>()
    var levelWordsFailed = arrayOf<Long>()

    var levelMastered = false


    constructor(name: String){
        levelName = name
    }

    constructor(name: String, id: Long, wordList: ArrayList<Word>){
        levelName = name
        levelID = id
        levelWordList = wordList
    }

    override fun toString(): String {
        return levelName
    }
}