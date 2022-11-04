package com.example.noteapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1)
abstract class NoteAppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

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