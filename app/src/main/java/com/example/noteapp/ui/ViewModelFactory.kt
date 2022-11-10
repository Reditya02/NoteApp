package com.example.noteapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.data.Repository
import com.example.noteapp.data.room.NoteAppDatabase
import com.example.noteapp.ui.add.AddViewModel
import com.example.noteapp.ui.detail.DetailViewModel
import com.example.noteapp.ui.home.HomeViewModel
import com.example.noteapp.ui.login.LoginViewModel
import com.example.noteapp.ui.register.RegisterViewModel

class ViewModelFactory (application: Application) :
    ViewModelProvider.Factory {

    private val noteDao = NoteAppDatabase.getDatabase(application).noteDao()
    private val userDao = NoteAppDatabase.getDatabase(application).userDao()

    private val repository = Repository(noteDao, userDao)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            modelClass.isAssignableFrom(AddViewModel::class.java) -> AddViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(repository) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository) as T
            else -> throw Throwable("ViewModel not Valid")
        }
    }
}