package com.example.au22_flashcard

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class AddWords : AppCompatActivity(), CoroutineScope {

    private lateinit var engET: EditText
    private lateinit var sweET: EditText
    lateinit var saveBtn: Button
    lateinit var db: AppDatabase
    lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_words)

        job = Job()


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "word_database"
        )
            .fallbackToDestructiveMigration()
            .build()
        //db = AppDatabase.getInstance(this)


        engET = findViewById(R.id.engET)
        sweET = findViewById(R.id.sweET)
        saveBtn = findViewById(R.id.addBtn)


        saveBtn.setOnClickListener {
          addToDB()
        }

    }

    //   override fun onDestroy() {
    //     super.onDestroy()
    //   job.cancel()
    //}


        fun addToDB() {
            if (engET.text.isEmpty() || sweET.text.isEmpty()) {
                Toast.makeText(this, "Error: field empty", Toast.LENGTH_LONG).show()
            } else {
                val swedish = sweET.text.toString().trim()
                val english = engET.text.toString().trim()
                val inputWord = Word(0, english, swedish)

                Toast.makeText(this, "added", Toast.LENGTH_LONG).show()

                launch(Dispatchers.IO) {

                    db.wordDao.insert(inputWord)

                }


                finish()
            }
        }
    }

