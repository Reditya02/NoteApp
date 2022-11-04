package com.example.noteapp

import android.util.Patterns.EMAIL_ADDRESS

object TextChecker {
    fun checkEmail(email: String) =
        if (email.isEmpty()) {
            TextMessage.TextEmpty
        } else if (EMAIL_ADDRESS.matcher(email).matches()) {
            TextMessage.Ok
        } else {
            TextMessage.EmailFormatInvalid
        }

    fun checkPassword(password: String) =
        if (password.length < 8) {
            TextMessage.PasswordTooShort
        } else if (password.isEmpty()) {
            TextMessage.TextEmpty
        } else {
            TextMessage.Ok
        }

    fun checkPasswordRetype(password: String, retypePassword: String) =
        if (password == retypePassword) {
            TextMessage.Ok
        } else {
            TextMessage.PasswordNotMatch
        }

    fun checkTitle(title: String) =
        if (title.isEmpty()) {
            TextMessage.TextEmpty
        } else {
            TextMessage.Ok
        }

    fun checkDescription(description: String) =
        if (description.isEmpty()) {
            TextMessage.TextEmpty
        } else {
            TextMessage.Ok
        }
}