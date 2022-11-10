package com.example.noteapp.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.TextChecker
import com.example.noteapp.TextMessage
import com.example.noteapp.data.model.Note
import com.example.noteapp.data.Repository
import kotlinx.coroutines.launch

class AddViewModel(private val repository: Repository) : ViewModel() {
    private var _edtTitleResponse = MutableLiveData<TextMessage>()
    val edtTitleResponse: LiveData<TextMessage> = _edtTitleResponse

    private var _edtDescriptionResponse = MutableLiveData<TextMessage>()
    val edtDescriptionResponse: LiveData<TextMessage> = _edtDescriptionResponse

//    fun addNote(note: Note) {
//        Database.addNote(note)
//    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
        }
    }

    fun checkEdtTitle(title: String) {
        _edtTitleResponse.value = TextChecker.checkTitle(title)
    }

    fun checkEdtDescription(description: String) {
        _edtDescriptionResponse.value = TextChecker.checkDescription(description)
    }
}