package com.matthewcannefax.learnsightwords.word

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.matthewcannefax.learnsightwords.R
import com.matthewcannefax.learnsightwords.settings.SettingsActivity
import com.matthewcannefax.learnsightwords.speech.SpeechHelper

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecognitionListener{


    private lateinit var wordViewModel: WordViewModel

    private val wordView by lazy {
        findViewById<TextView>(R.id.wordView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        wordViewModel.currentSightWord.observe(this, Observer {
            wordView.text = it.word
        })



        fab.setOnClickListener { view ->
            SpeechHelper.getSpeechInput(this).setRecognitionListener(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        var b: Boolean = false
        when (item.itemId) {
            R.id.action_settings ->
                startActivity(settingsIntent)
            else -> b = super.onOptionsItemSelected(item)
        }

        return b;
    }


    override fun onResults(p0: Bundle?) {
        val isCorrect = SpeechHelper.speechResult(p0!!, wordViewModel.currentSightWord.value!!)
        if (isCorrect){
            wordViewModel.nextWord()
        }
    }

    private fun returnFabAnimation(){
        fab.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.ic_mic_empty
        ))
    }

    //animate the mic button here
    override fun onReadyForSpeech(p0: Bundle?) {
        fab.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.ic_mic
        ))
    }

    //animate the mic button here too
    override fun onEndOfSpeech() {
        returnFabAnimation()
    }

    //region unused speech recognition methods
    override fun onRmsChanged(p0: Float) {

    }

    override fun onBufferReceived(p0: ByteArray?) {

    }

    override fun onPartialResults(p0: Bundle?) {
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
    }

    override fun onError(p0: Int) {
        returnFabAnimation()
        Toast.makeText(this,
            when(p0){
                SpeechRecognizer.ERROR_AUDIO -> "audio"
                SpeechRecognizer.ERROR_CLIENT -> "client"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "permission"
                SpeechRecognizer.ERROR_NETWORK -> "network"
                SpeechRecognizer.ERROR_NO_MATCH -> "no match"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "busy"
                SpeechRecognizer.ERROR_SERVER -> "server"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "speech timeout"
                else -> "understand"
            },
            Toast.LENGTH_LONG).show()

    }

    //endregion

}
