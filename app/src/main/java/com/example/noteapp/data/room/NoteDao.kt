package com.example.noteapp.data.room

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.example.noteapp.data.model.Note

@Dao
interface NoteDao {
    @Transaction
    @Query("select * from note where owner_email like :email limit 1")
    suspend fun getAllNote(email: String): List<Note>

    @Update(onConflict = IGNORE)
    fun updateNote(note: Note)

    @Insert(onConflict = IGNORE)
    fun addNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}