package com.matthewcannefax.learnsightwords.word

class Level {
    var levelName: String = ""
    var levelID: Long = -1
    var levelWordList = arrayListOf<Word>()

    //these are to hold arrays of the word IDs
    var levelWordsMastered = arrayOf<Long>()
    var levelWordsFailed = arrayOf<Long>()

    var levelMastered = false

    constructor(name: String, id: Long, wordList: ArrayList<Word>){
        levelName = name
        levelID = id
        levelWordList = wordList
    }

}