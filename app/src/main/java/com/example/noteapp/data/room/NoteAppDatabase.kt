package com.example.noteapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.data.model.Note
import com.example.noteapp.data.model.User


@Database(entities = [Note::class, User::class], version = 1)
abstract class NoteAppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: NoteAppDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NoteAppDatabase {
            if (INSTANCE == null) {
                synchronized(NoteAppDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteAppDatabase::class.java,
                        "note"
                    ).build()
                }
            }
            return INSTANCE as NoteAppDatabase
        }
    }
}