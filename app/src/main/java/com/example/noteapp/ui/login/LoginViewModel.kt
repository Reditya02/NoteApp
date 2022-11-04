package com.example.noteapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.TextChecker
import com.example.noteapp.TextMessage

class LoginViewModel : ViewModel() {
    private val _edtEmailResponse = MutableLiveData<TextMessage>()
    val edtEmailResponse: LiveData<TextMessage> = _edtEmailResponse

    private val _edtPasswordResponse = MutableLiveData<TextMessage>()
    val edtPasswordResponse: LiveData<TextMessage> = _edtPasswordResponse

    fun checkEdtEmail(email: String) {
        _edtEmailResponse.value = TextChecker.checkEmail(email)
    }

    fun checkEdtPassword(password: String) {
        _edtPasswordResponse.value = TextChecker.checkPassword(password)
    }
}