package com.example.tmsproject2.bd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisterDatabaseDao {

    @Insert
    suspend fun insert(register: RegisterEntity)


    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>


    @Query("SELECT * FROM Register_users_table WHERE user_email LIKE :userEmail")
    suspend fun getUserEmail(userEmail: String): RegisterEntity?
}