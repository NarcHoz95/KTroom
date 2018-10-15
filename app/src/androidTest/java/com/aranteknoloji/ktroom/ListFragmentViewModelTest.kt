package com.aranteknoloji.ktroom

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.aranteknoloji.ktroom.db.AppDatabase
import com.aranteknoloji.ktroom.db.User
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class ListFragmentViewModelTest {

    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase::class.java).build()
    }

    @After
    @Throws(IOException::class)
    fun close() {
        appDatabase.close()
    }

    @Test
    fun insertUserTest() {
        val user = User(null, "TestName", "TestLastName")
        appDatabase.userDao().insertUser(user)
        val usersFromDB = appDatabase.userDao().allUsers()
        assertEquals(user.firstname, usersFromDB.get(0).firstname)
    }

    //User LiveData testing
    @Throws(InterruptedException::class)
    fun <T> LiveData<T>.getValueBlocking(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)
        val innerObserver = Observer<T> {
            value = it
            latch.countDown()
        }
        observeForever(innerObserver)
        latch.await(2, TimeUnit.SECONDS)
        return value
    }

    @Test
    fun insertUser_LiveDataTest() {
        val user = User(null, "TestName", "TestLastName")
        appDatabase.userDao().insertUser(user)
        val liveDataUserFromDb = appDatabase.userDao().allUsersLiveData()
        val usersFromDb = liveDataUserFromDb.getValueBlocking()
        assertEquals(user.firstname, usersFromDb?.get(0)?.firstname)
    }


    //viewModel test
    @Ignore
    @Test
    fun viewModelTest() {
        val viewModel = mock(ListFragmentViewModel::class.java)
        verify(viewModel).user()
    }
}