package com.example.noteapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.TextChecker
import com.example.noteapp.TextChecker.checkPasswordRetype
import com.example.noteapp.TextMessage

class RegisterViewModel : ViewModel() {
    private val _edtEmailResponse = MutableLiveData<TextMessage>()
    val edtEmailResponse: LiveData<TextMessage> = _edtEmailResponse

    private val _edtPasswordResponse = MutableLiveData<TextMessage>()
    val edtPasswordResponse: LiveData<TextMessage> = _edtPasswordResponse

    private val _edtRetypePasswordResponse = MutableLiveData<TextMessage>()
    val edtRetypePasswordResponse: LiveData<TextMessage> = _edtRetypePasswordResponse

    fun checkEdtEmail(email: String) {
        _edtEmailResponse.value = TextChecker.checkEmail(email)
    }

    fun checkEdtPassword(password: String) {
        _edtPasswordResponse.value = TextChecker.checkPassword(password)
    }

    fun checkEdtRetypePassword(password: String, retypePassword: String) {
        _edtRetypePasswordResponse.value = checkPasswordRetype(password, retypePassword)
    }
}