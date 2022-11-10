package com.example.noteapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.TextChecker
import com.example.noteapp.TextMessage
import com.example.noteapp.data.Repository
import com.example.noteapp.data.model.User
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {
    private val _edtEmailResponse = MutableLiveData<TextMessage>()
    val edtEmailResponse: LiveData<TextMessage> = _edtEmailResponse

    private val _edtPasswordResponse = MutableLiveData<TextMessage>()
    val edtPasswordResponse: LiveData<TextMessage> = _edtPasswordResponse

    private val _isCanLogin = MutableLiveData<Boolean>()
    val isCanLogin: LiveData<Boolean> = _isCanLogin

    fun checkEdtEmail(email: String) {
        _edtEmailResponse.value = TextChecker.checkEmail(email)
    }

    fun checkEdtPassword(password: String) {
        _edtPasswordResponse.value = TextChecker.checkPassword(password)
    }

    fun checkLogin(user: User) {
        viewModelScope.launch {
            _isCanLogin.value = repository.getAllUser().contains(user)
        }
    }

}