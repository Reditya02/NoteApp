package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Query("select * from note")
    suspend fun getAllNote(): List<Note>

    @Query("select * from note where id like :id")
    fun getNoteById(id: Int): Note

    @Update(onConflict = IGNORE)
    fun updateNote(note: Note)

    @Insert(onConflict = IGNORE)
    fun addNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}