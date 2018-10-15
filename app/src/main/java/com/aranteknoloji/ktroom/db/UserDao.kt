package com.aranteknoloji.ktroom.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun allUsers(): List<User>

    @Query("SELECT * FROM user")
    fun allUsersLiveData(): LiveData<List<User>>
}