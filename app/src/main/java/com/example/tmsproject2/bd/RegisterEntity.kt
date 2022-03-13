package com.example.tmsproject2.bd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Register_users_table")
data class RegisterEntity(

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "user_email")
    var userEmail: String,

    @ColumnInfo(name = "password_text")
    var passwrd: String
)
