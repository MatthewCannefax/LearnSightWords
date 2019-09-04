package com.matthewcannefax.learnsightwords.settings

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matthewcannefax.learnsightwords.R
import com.matthewcannefax.learnsightwords.word.SightWordLevel
import com.matthewcannefax.learnsightwords.word.Word
import kotlinx.android.synthetic.main.activity_main.*

import java.util.logging.Level

class SettingsActivity : AppCompatActivity() {

    private lateinit var settingsViewModel: SettingsViewModel

    //the spinner that holds Strings for all the levels currently in the database
    private val levelSpinner by lazy {
        findViewById<Spinner>(R.id.level_spinner)
    }

    //button to add a new level
    private val addLevelButton by lazy {
        findViewById<ImageButton>(R.id.add_level_button)
    }

    //recycler view that shows all the words for the selected level
    private val wordRecyclerView by lazy {
        findViewById<RecyclerView>(R.id.word_recyclerview)
    }

    //edittext where the user types a new word to add to the selected level
    private val newWordEditText by lazy {
        findViewById<EditText>(R.id.new_word_edittext)
    }

    //the button that adds the currently typed word to the database under the currently selected level
    private val addNewWordButton by lazy {
        findViewById<Button>(R.id.add_word_button)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)

        //initialize the settingsviewmodel
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)

        settingsViewModel.levelListLive.observe(this, Observer {
            setLevelSpinnerAdapter(it)
        })

        //setup the observer for the current level
        //when the level is changed the observer will change the list that is displayed in the
        //recyclerview
        settingsViewModel.currentLevel.observe(this, Observer {


        })
        settingsViewModel.currentWordList.observe(this, Observer {
            setRecyclerAdapter()
        })


        //the click listener for the spinner
        //when a level is clicked by the user the currentLevel is changed and the observer
        //will change the list of words displayed inside the recyclerview
        levelSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }


        //a new level is added to the database
        //there is no way to immediately add the level to the database, a new level will only be added
        //if a word is actually added to the list
        addLevelButton.setOnClickListener{view ->
            settingsViewModel.addNewLevel()

        }

        //the word that is typed by the user in the edittext is added to the currently selected
        //level in the spinner
        addNewWordButton.setOnClickListener{view ->
            settingsViewModel.addWordToLevel(newWordEditText.text.toString())
        }

    }

    private fun setLevelSpinnerAdapter(levelList: List<Int>){
        val spinnerAdapter = ArrayAdapter<Int>(this, android.R.layout.simple_spinner_dropdown_item, levelList)
        levelSpinner.adapter = spinnerAdapter
    }

    //set up the recycler adapter for the recyclerview
    private fun setRecyclerAdapter() {
        val wordRecyclerAdapter = WordRecyclerAdapter(settingsViewModel.currentWordList.value!!)
        wordRecyclerView.adapter = wordRecyclerAdapter
        wordRecyclerView.layoutManager = LinearLayoutManager(this)
    }


}
