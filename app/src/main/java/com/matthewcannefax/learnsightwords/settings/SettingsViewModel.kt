package com.matthewcannefax.learnsightwords.settings

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.matthewcannefax.learnsightwords.sample.SampleWords
import com.matthewcannefax.learnsightwords.word.SightWordLevel
import java.util.logging.Level

class SettingsViewModel(app: android.app.Application) : AndroidViewModel(app) {
    val currentLevel = MutableLiveData<SightWordLevel>()
    var levelList = ArrayList<SightWordLevel>()


    init{
        levelList.add(SampleWords.getLevelOne().get(0))
        currentLevel.value = levelList.get(0)
    }

    fun createNewLevel(name: String){
        levelList.add(SightWordLevel(name))
        currentLevel.value = levelList.get(levelList.size - 1)
    }
}