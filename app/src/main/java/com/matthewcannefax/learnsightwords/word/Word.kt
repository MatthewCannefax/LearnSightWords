package com.matthewcannefax.learnsightwords.word

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import com.matthewcannefax.learnsightwords.room.WORD_LEVEL_COLUMN
import com.matthewcannefax.learnsightwords.room.WORD_TABLE_NAME
import com.matthewcannefax.learnsightwords.room.WORD_WORD_COLUMN
import java.util.logging.Level

@Entity(tableName = WORD_TABLE_NAME)
class Word(word: String) {

    @PrimaryKey
    @ColumnInfo(name = WORD_WORD_COLUMN)
    var word: String = word

    //levels will be divided up by integers
    @ColumnInfo(name = WORD_LEVEL_COLUMN)
    var level: Int = 1

    fun getLevelString(): String{
        return String.format("Level %s", level)
    }
}