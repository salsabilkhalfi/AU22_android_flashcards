package com.example.au22_flashcard

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDao {

    @Insert
    fun insert(word: Word)


    @Delete
    fun deleteWord(word: Word)

    @Update //
    fun updateWord(word: Word)



    @Query("SELECT * FROM word_table")
   fun getAllWords(): List<Word>





}