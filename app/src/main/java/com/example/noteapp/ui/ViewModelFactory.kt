package com.example.noteapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.data.NoteDao
import com.example.noteapp.data.Repository
import com.example.noteapp.ui.add.AddViewModel
import com.example.noteapp.ui.detail.DetailViewModel
import com.example.noteapp.ui.home.HomeViewModel

class ViewModelFactory (dao: NoteDao) :
    ViewModelProvider.Factory {

    private val repository = Repository(dao)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            modelClass.isAssignableFrom(AddViewModel::class.java) -> AddViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository) as T
            else -> throw Throwable("ViewModel not Valid")
        }
    }
}