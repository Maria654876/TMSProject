package com.example.tmsproject2.login

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.Observable
import com.example.tmsproject2.bd.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class LoginViewModel(
    private val repository: RegisterRepository,
    application: Application)
    : AndroidViewModel(application), Observable {

    val users = repository.users

    @Bindable
    val inputUserEmail = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoRegister = MutableLiveData<Boolean>()

    val navigatetoRegister: LiveData<Boolean>
        get() = _navigatetoRegister


    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUserEmail = MutableLiveData<Boolean>()

    val errotoastUserEmail: LiveData<Boolean>
        get() = _errorToastUserEmail

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword


    fun signUP() {
        _navigatetoRegister.value = true
    }

    fun loginButton() {
        if (inputUserEmail.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val usersEmails = repository.getUserEmail(inputUserEmail.value!!)
                if (usersEmails != null) {
                    if(usersEmails.passwrd == inputPassword.value){
                        inputUserEmail.value = null
                        inputPassword.value = null
                    }else{
                        _errorToastInvalidPassword.value = true
                    }
                } else {
                    _errorToastUserEmail.value = true
                }
            }
        }
    }




    fun doneNavigatingRegiter() {
        _navigatetoRegister.value = false
    }


    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }


    fun donetoastErrorUsername() {
        _errorToastUserEmail .value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastInvalidPassword() {
        _errorToastInvalidPassword .value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}