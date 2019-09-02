package com.matthewcannefax.learnsightwords.word

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matthewcannefax.learnsightwords.room.WORD_TABLE_NAME
import com.matthewcannefax.learnsightwords.room.WORD_WORD_COLUMN
import java.util.logging.Level

@Entity(tableName = WORD_TABLE_NAME)
class Word(word: String) {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = WORD_WORD_COLUMN)
    var word: String = word


//    var level: SightWordLevel = SightWordLevel()
}