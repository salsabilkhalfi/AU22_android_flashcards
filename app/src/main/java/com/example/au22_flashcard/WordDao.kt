package com.example.au22_flashcard

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // handerar vid dubbletter
   suspend fun insert(word: Word) //suspens- to handle long running tasks without blocking the main thread


    @Delete
    suspend fun deleteWord(word: Word)

    @Update //
    fun updateWord(word: Word)



    @Query("SELECT * FROM word_table")
   fun getAllWords(): List<Word>






}