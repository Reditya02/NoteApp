package com.example.noteapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.model.Note
import com.example.noteapp.data.Repository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private var _listNote = MutableLiveData<List<Note>>()
    val listNote: LiveData<List<Note>> = _listNote

    fun getData(email: String) {
        viewModelScope.launch {
            _listNote.value = repository.getAllNote(email)

        }
    }
}