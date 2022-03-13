package com.example.tmsproject2.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmsproject2.bd.RegisterRepository

class RegisterViewModelFactory(
    private val repository: RegisterRepository,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(repository, application) as T
        }
        throw IllegalAccessException("Unknown View Model Class")
    }
}