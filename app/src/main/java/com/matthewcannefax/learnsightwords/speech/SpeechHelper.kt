package com.matthewcannefax.learnsightwords.speech

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.matthewcannefax.learnsightwords.SPEECH_REQUEST_CODE
import com.matthewcannefax.learnsightwords.word.Word

class SpeechHelper {
    companion object{

        fun getSpeechInput(context: Context): SpeechRecognizer{
            val speechRecognizer: SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
            val speechIntent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            speechIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.packageName)
            speechRecognizer.startListening(speechIntent)

            return speechRecognizer
        }

        fun speechResult(bundle: Bundle, currentWord: Word): Boolean{
            var isCorrect = false

            val matches: ArrayList<String> = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            matches.forEach(){
                if(it.toLowerCase().equals(currentWord.word.toLowerCase())){
                    isCorrect = true
                }
            }

            return isCorrect
        }
    }
}