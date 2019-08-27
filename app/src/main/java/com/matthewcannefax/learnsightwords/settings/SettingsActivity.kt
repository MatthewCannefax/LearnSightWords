package com.matthewcannefax.learnsightwords.settings

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matthewcannefax.learnsightwords.R
import com.matthewcannefax.learnsightwords.word.SightWordLevel
import kotlinx.android.synthetic.main.activity_main.*

import java.util.logging.Level

class SettingsActivity : AppCompatActivity() {

    private lateinit var settingsViewModel: SettingsViewModel

    private val levelSpinner by lazy {
        findViewById<Spinner>(R.id.level_spinner)
    }

    private val addLevelButton by lazy {
        findViewById<ImageButton>(R.id.add_level_button)
    }

    private val wordRecyclerView by lazy {
        findViewById<RecyclerView>(R.id.word_recyclerview)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)

        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)

        settingsViewModel.currentLevel.observe(this, Observer {
            setRecyclerAdapter(it)

            val spinnerAdapter: ArrayAdapter<SightWordLevel> = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, settingsViewModel.levelList)
            levelSpinner.adapter = spinnerAdapter
            levelSpinner.setSelection(settingsViewModel.levelList.size - 1)
        })

        levelSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val level: SightWordLevel = settingsViewModel.levelList.get(levelSpinner.selectedItemPosition)
                setRecyclerAdapter(level)
            }
        }


        addLevelButton.setOnClickListener{view ->
            settingsViewModel.createNewLevel("Level " + (settingsViewModel.levelList.size + 1))
        }

    }

    private fun setRecyclerAdapter(it: SightWordLevel) {
        val wordRecyclerAdapter: WordRecyclerAdapter = WordRecyclerAdapter(it.levelWordList)
        wordRecyclerView.adapter = wordRecyclerAdapter
        wordRecyclerView.layoutManager = LinearLayoutManager(this)
    }


}
