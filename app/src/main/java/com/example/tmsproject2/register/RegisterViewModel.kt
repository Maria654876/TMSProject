package com.example.tmsproject2.register

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.example.tmsproject2.bd.RegisterEntity
import com.example.tmsproject2.bd.RegisterRepository
import androidx.databinding.Observable
import kotlinx.coroutines.*
import java.lang.Appendable
import java.util.*

class RegisterViewModel(private val repository: RegisterRepository, application: Application) : AndroidViewModel(application), Observable {
    init {
        Log.i("MYTAG", "init")
    }


    private var userdata: String? = null

    @Bindable
    val inputUserEmail = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUserEmail = MutableLiveData<Boolean>()

    val errotoastUserEmail: LiveData<Boolean>
        get() = _errorToastUserEmail


    fun sumbitButton() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if ( inputUserEmail.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
//            withContext(Dispatchers.IO) {
                val usersEmails = repository.getUserEmail(inputUserEmail.value!!)
                Log.i("MYTAG", usersEmails.toString() + "------------------")
                if (usersEmails != null) {
                    _errorToastUserEmail.value = true
                    Log.i("MYTAG", "Inside if Not null")
                } else {
                    Log.i("MYTAG", "OK im in")
                    val email = inputUserEmail.value!!
                    val password = inputPassword.value!!
                    Log.i("MYTAG", "insidi Sumbit")
                    insert(RegisterEntity(0, email, password))
                    inputUserEmail.value = null
                    inputPassword.value = null
                    _navigateto.value = true
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastUserEmail() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting  username")
    }

    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}