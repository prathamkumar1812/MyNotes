package com.example.mynotes.ViewModle

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mynotes.modles.Notes
import com.example.mynotes.modles.NotesDao
import com.example.mynotes.modles.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepository: NotesRepository): ViewModel() {
    fun getNotes():LiveData<List<Notes>>{
        return notesRepository.getNotes()
    }
    fun InsetNotes(notes: Notes){
        GlobalScope.launch(Dispatchers.IO) {
            notesRepository.InsertNotes(notes)
        }
    }
    fun DeleteNotes(notes: Notes){
        GlobalScope.launch(Dispatchers.IO) {
            notesRepository.DeleteNotes(notes)
        }
    }
    fun searchlist(t:String):LiveData<List<Notes>>{
        return notesRepository.searchlist(t)
    }
}