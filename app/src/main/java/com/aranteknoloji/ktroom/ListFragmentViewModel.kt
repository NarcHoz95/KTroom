package com.aranteknoloji.ktroom

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.aranteknoloji.ktroom.db.AppDatabase
import com.aranteknoloji.ktroom.db.User

class ListFragmentViewModel(val app: Application): AndroidViewModel(app) {

    private val TAG = ListFragmentViewModel::class.java.simpleName

    fun user(): LiveData<List<User>> {
        Log.w(TAG, "user()")
        val appdb = AppDatabase.getInstance(app)
        return appdb!!.userDao().allUsersLiveData()
    }

    fun insertUser(user: User) {
        Log.w(TAG, "insertUser()")
        val appdb = AppDatabase.getInstance(app)
        appdb!!.userDao().insertUser(user)
    }
}