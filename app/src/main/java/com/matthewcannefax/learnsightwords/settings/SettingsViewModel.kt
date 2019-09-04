package com.matthewcannefax.learnsightwords.settings

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.matthewcannefax.learnsightwords.room.LEVEL_FIRST_WORD_DUMMY
import com.matthewcannefax.learnsightwords.room.WordRepo
import com.matthewcannefax.learnsightwords.word.Word

class SettingsViewModel(app: android.app.Application) : AndroidViewModel(app) {
    val currentLevel = MutableLiveData<Int>()
    var currentWordList: LiveData<List<Word>>

    var levelListLive: LiveData<List<Int>>
    var levelList: List<Int>
    val mRepo: WordRepo;

    init{
        mRepo = WordRepo(app)
        levelList = mRepo.allLevels
        levelListLive = mRepo.allLevelsLive

        currentLevel.value = levelList.get(0)
        currentWordList = mRepo.getLevelWords(currentLevel.value!!)

    }

    fun addWordToLevel(editTextString: String){
        val word = Word(editTextString)
        word.level = currentLevel.value!!
        mRepo.insert(word)//it shows up in the recyclerview but not showing in the database when I look at sqlite spy
    }

    //in order to add a new level there needs to be a new word associated with that level
    //that's how the db works right now
    //create a new work using a dummy code that is not a word at all
    //this word will later be removed from the db
    fun addNewLevel(){
        val dummyWord = Word(LEVEL_FIRST_WORD_DUMMY)
        dummyWord.level = levelList.size + 1
        mRepo.insert(dummyWord)
    }

    fun setLevel(levelInt: Int){
        currentLevel.value = levelList.get(levelInt)
        setWordList()
    }

    fun setWordList(){
        currentWordList = mRepo.getLevelWords(currentLevel.value!!)
    }

    fun getStringLevelList(): List<String>{
        var stringList = ArrayList<String>()
        levelList.forEach {
            stringList.add(String.format("Level %s", it))
        }
        return stringList
    }

    fun addToLevelArray(){
        //need to figure out a way to add a level...without adding a level
        //since the levels only exist within the word, its impossible to insert a new level in the database
        //before adding the first word of that new level
        //could possibly use a placeholder word or empty string, but I'd rather not do that
    }


}