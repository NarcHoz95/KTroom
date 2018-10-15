package com.aranteknoloji.ktroom.db

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest{

    var mUserDao: UserDao? = null

    @Before
    fun createDB() {
        val context: Context = InstrumentationRegistry.getTargetContext()
        val mdb = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        mUserDao = mdb.userDao()
    }

    @After
    fun closeDB() {
//        mdb.close()
    }

    @Test
    fun writeTestUserCase() {
        val user = User(null, "Arda", "Kucukoz")
        mUserDao?.insertUser(user)
        val users: List<User> = mUserDao!!.allUsers()
        assertEquals(user.firstname, users.get(0).firstname)
        assertEquals(user.lastname, users.get(0).lastname)
    }
}