package com.sample.retrofitmvvmrecycleview.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.retrofitmvvmrecycleview.model.User
import com.sample.retrofitmvvmrecycleview.repository.UserRepository
import com.sample.retrofitmvvmrecycleview.utils.BaseUtil
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import petros.efthymiou.groovy.utils.MainCoroutineScopeRule

class UserViewModelTest : BaseUtil() {

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
        return UserViewModel(userRepository)
    }

    private suspend fun mockSuccessCase(): UserViewModel {
        whenever(userRepository.getAllUsers()).thenReturn(
            expectedResults
        )
        return UserViewModel(userRepository)
    }

}