package com.example.noteapp.data

import com.example.noteapp.data.model.Note
import com.example.noteapp.data.model.User
import com.example.noteapp.data.room.NoteDao
import com.example.noteapp.data.room.UserDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(private val noteDao: NoteDao, private val userDao: UserDao) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    suspend fun getAllNote(email: String): List<Note> = noteDao.getAllNote(email)

    fun addNote(note: Note) = executorService.execute { noteDao.addNote(note) }

    fun updateNote(note: Note) = executorService.execute { noteDao.updateNote(note) }

    fun deleteNote(note: Note) = executorService.execute { noteDao.deleteNote(note) }

    fun register(user: User) = executorService.execute { userDao.register(user) }

    suspend fun getAllUser(): List<User> = userDao.getAllUser()
}