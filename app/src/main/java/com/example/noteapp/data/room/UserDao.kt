package com.example.noteapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.noteapp.data.model.User

@Dao
interface UserDao {
    @Insert
    fun register(user: User)

    @Query("select * from user")
    suspend fun getAllUser(): List<User>
}