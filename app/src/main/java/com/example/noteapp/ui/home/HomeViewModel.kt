package com.example.noteapp.ui.home

import androidx.lifecycle.*
import com.example.noteapp.data.Database
import com.example.noteapp.data.Note
import com.example.noteapp.data.Repository
import com.example.noteapp.ui.add.AddViewModel
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private var _listNote = MutableLiveData<List<Note>>()
    val listNote: LiveData<List<Note>> = _listNote

    fun getDummyData() {
        _listNote.value = Database.listNote
    }

    fun getData() {
        viewModelScope.launch {
            _listNote.value = repository.getAllNote()

        }
    }
}