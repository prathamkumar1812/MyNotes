package com.example.mynotes.ViewModle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.mynotes.modles.NotesRepository

class NotesViewModleFactory(val notesRepository: NotesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepository) as T
    }
}