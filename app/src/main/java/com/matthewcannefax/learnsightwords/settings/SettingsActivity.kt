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

    //region VIEWS
    private val levelSpinner by lazy {
        //the spinner that holds Strings for all the levels currently in the database
        findViewById<Spinner>(R.id.level_spinner)
    }

    private val addLevelButton by lazy {
        //button to add a new level
        findViewById<ImageButton>(R.id.add_level_button)
    }

    private val wordRecyclerView by lazy {
        //recycler view that shows all the words for the selected level
        findViewById<RecyclerView>(R.id.word_recyclerview)
    }

    private val newWordEditText by lazy {
        //edittext where the user types a new word to add to the selected level
        findViewById<EditText>(R.id.new_word_edittext)
    }

    private val addNewWordButton by lazy {
        //the button that adds the currently typed word to the database under the currently selected level
        findViewById<Button>(R.id.add_word_button)
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)

        //initialize the settingsviewmodel
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)

        settingsViewModel.levelListLive.observe(this, Observer {
            setLevelSpinnerAdapter(it)
        })

        settingsViewModel.currentLevel.observe(this, Observer {
            //setup the observer for the current level
            //when the level is changed the observer will change the list that is displayed in the
            //recyclerview
        })

        settingsViewModel.currentWordList.observe(this, Observer {
            setRecyclerAdapter()
        })

        levelSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            //the click listener for the spinner
            //when a level is clicked by the user the currentLevel is changed and the observer
            //will change the list of words displayed inside the recyclerview
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }//end levelSpinner onItemClick

        addLevelButton.setOnClickListener{view ->
            //a new level is added to the database
            //there is no way to immediately add the level to the database, a new level will only be added
            //if a word is actually added to the list
            settingsViewModel.addNewLevel()

        }

        addNewWordButton.setOnClickListener{view ->
            //the word that is typed by the user in the edittext is added to the currently selected
            //level in the spinner
            settingsViewModel.addWordToLevel(newWordEditText.text.toString())
        }

    }//end onCreate

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
