package com.example.tmsproject2.service

interface AppPreferences {
    fun isSaveCredentialsSelected(): Boolean
    fun setSaveCredentialsSelected(isSelected: Boolean)
    fun getToken(): String
}