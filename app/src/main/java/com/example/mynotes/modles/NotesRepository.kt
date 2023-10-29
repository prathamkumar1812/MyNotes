package com.example.mynotes.modles

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    suspend fun InsertNotes(notes: Notes){
        notesDao.InsertNotes(notes)
    }
    fun getNotes():LiveData<List<Notes>>{
        return notesDao.getlistofNotes()
    }
    suspend fun DeleteNotes(notes: Notes){
        notesDao.DeleteNotes(notes)
    }
    fun searchlist(t:String):LiveData<List<Notes>>{
        return notesDao.seacrhNotes(t)
    }
}