package com.matthewcannefax.learnsightwords.word

class Word {
    constructor(string: String){
        word = string
    }
    var word: String = ""
    var wordID: Long = -1
    var correct = false
    var correctStreak: Int = 0
}