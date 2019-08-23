package com.matthewcannefax.learnsightwords.user

import com.matthewcannefax.learnsightwords.word.Word

class User {
    var name: String = ""
    var failedWords = arrayListOf<Word>()
    var masteredWords = arrayListOf<Word>()

    //This constructor to create a new user
    constructor(name: String){
        this.name = name
    }

    //This constructor to retrieve user data from the database
    constructor(name: String, failedWords: ArrayList<Word>, masteredWords: ArrayList<Word>){
        this.name = name
        this.failedWords = failedWords
        this.masteredWords = masteredWords
    }
}