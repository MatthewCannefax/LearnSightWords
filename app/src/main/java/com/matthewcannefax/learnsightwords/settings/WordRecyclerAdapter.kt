package com.matthewcannefax.learnsightwords.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.matthewcannefax.learnsightwords.R
import com.matthewcannefax.learnsightwords.word.Word

class WordRecyclerAdapter(val wordList: ArrayList<Word>) : RecyclerView.Adapter<WordRecyclerAdapter.WordViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val mInflater = LayoutInflater.from(parent.context).inflate(R.layout.settings_word_item, parent, false)
        return WordViewHolder(mInflater)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.nameView.setText(wordList.get(position).word)
    }


    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val nameView by lazy {
            itemView.findViewById<TextView>(R.id.word_textview)
        }

        val deleteButton by lazy {
            itemView.findViewById<ImageButton>(R.id.remove_word_button)
        }

        init {
            deleteButton.setOnClickListener{view ->
                Toast.makeText(view.context, "delete this word", Toast.LENGTH_LONG).show()
            }
        }


    }
}