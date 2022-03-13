package com.example.tmsproject2.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RegisterEntity::class], version = 1, exportSchema = false)
abstract class RegisterDataBase : RoomDatabase(){

    abstract val registerDatabaseDao: RegisterDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: RegisterDataBase? = null


        fun getInstance(context: Context): RegisterDataBase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RegisterDataBase::class.java,
                        "user_details_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}