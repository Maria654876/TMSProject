package com.example.tmsproject2.bd

import android.provider.ContactsContract
import android.util.Log

class RegisterRepository(private val dao: RegisterDatabaseDao) {

    val users = dao.getAllUsers()
    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    suspend fun getUserEmail(userEmail: String):RegisterEntity?{
        Log.i("MYTAG", "inside Repository Getusers fun ")
        return dao.getUserEmail(userEmail)
    }
}