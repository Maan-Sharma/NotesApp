package com.example.note

import androidx.lifecycle.LiveData

class NoteRepostory(private val noteDao: NoteDao) {
    val allNotes:LiveData<List<Note>> = noteDao.getAllNote()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
}