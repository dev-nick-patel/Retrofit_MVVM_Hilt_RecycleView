package com.sample.retrofitmvvmrecycleview.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.retrofitmvvmrecycleview.model.User
import com.sample.retrofitmvvmrecycleview.model.UserResponse
import com.sample.retrofitmvvmrecycleview.repository.UserRepository
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule
import petros.efthymiou.groovy.utils.getValueForTest
import retrofit2.Response
import java.lang.RuntimeException

class UserViewModelTest {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val userRepository: UserRepository = mock()
    private val userList = mock<List<User>>()
    private val expectedResults = Result.success(userList)
    private val expectedError = Result.failure<List<User>>(RuntimeException("Something went wrong"))


    @Test
    fun getUsersFromRepository(): Unit = runBlocking {
        val userViewModel = mockSuccessCase()
        userViewModel.getUsers()
        verify(userRepository, times(1)).getAllUsers()
    }

    @Test
    fun getSuccessResultOfUserList(): Unit = runBlocking {
        val userViewModel = mockSuccessCase()
        userViewModel.getUsers()
        assertEquals(expectedResults, userViewModel.users.value)
    }

    @Test
    fun getErrorWhenReceiveError(): Unit = runBlocking{
        val userViewModel = mockErrorCase()
        userViewModel.getUsers()
        assertEquals(expectedError, userViewModel.users.value)
    }

    private suspend fun mockErrorCase(): UserViewModel {
        whenever(userRepository.getAllUsers()).thenReturn(
            expectedError
        )
        val userViewModel = UserViewModel(userRepository)
        return userViewModel
    }

    private suspend fun mockSuccessCase(): UserViewModel {
        whenever(userRepository.getAllUsers()).thenReturn(
            expectedResults
        )
        val userViewModel = UserViewModel(userRepository)
        return userViewModel
    }

}