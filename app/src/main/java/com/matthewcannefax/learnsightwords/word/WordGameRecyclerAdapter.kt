package com.matthewcannefax.learnsightwords.word

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.matthewcannefax.learnsightwords.R

class WordGameRecyclerAdapter(val wordList: List<Word>) : RecyclerView.Adapter<WordGameRecyclerAdapter.WordGameViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordGameRecyclerAdapter.WordGameViewHolder {
        val mInflator = LayoutInflater.from(parent.context).inflate(R.layout.word_card_item, parent, false)
        return WordGameViewHolder(mInflator)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordGameRecyclerAdapter.WordGameViewHolder, position: Int) {
        holder.nameView.setText(wordList.get(position).word)
    }

    class WordGameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameView by lazy{
            itemView.findViewById<TextView>(R.id.wordView)
        }
        val imageView by lazy {
            itemView.findViewById<ImageView>(R.id.cardView)
        }
        init {

        }

    }
}