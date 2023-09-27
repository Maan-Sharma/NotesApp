package com.example.note

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R

class MainActivity : AppCompatActivity(), INoteAdepter {
    private lateinit var input:EditText

      lateinit var viewModel:NoteViewModel
     private lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input = findViewById(R.id.input)
        recyclerView = findViewById(R.id.rv)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adepter=NoteAdepter(this,this)
        recyclerView.adapter=adepter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]

        viewModel.allNote.observe(this, Observer {list->
            list?.let {
                adepter.updateList(it)

            }

        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text}Delect",Toast.LENGTH_LONG).show()
    }
    fun submitData(view:View){
        val noteText =input.text.toString()
        if (noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText,0))
            Toast.makeText(this,"$noteText Inserted",Toast.LENGTH_LONG).show()

        }

    }
}