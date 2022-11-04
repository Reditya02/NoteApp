package com.example.noteapp.data

import android.app.Application
import android.content.Context
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(private val noteDao: NoteDao) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    suspend fun getAllNote(): List<Note> = noteDao.getAllNote()

    fun getNoteById(id: Int): Note = noteDao.getNoteById(id)

    fun addNote(note: Note) = executorService.execute { noteDao.addNote(note) }

    fun updateNote(note: Note) = executorService.execute { noteDao.updateNote(note) }

    fun deleteNote(note: Note) = executorService.execute { noteDao.deleteNote(note) }
}