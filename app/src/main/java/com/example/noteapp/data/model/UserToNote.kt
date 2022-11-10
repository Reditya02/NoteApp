package com.example.noteapp.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserToNote(
    @Embedded
    val user: User,

    @Relation(
        parentColumn = "email",
        entityColumn = "owner_email"
    )
    val note: List<Note>
)
