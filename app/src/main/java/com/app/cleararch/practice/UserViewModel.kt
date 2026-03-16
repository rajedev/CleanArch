package com.app.cleararch.practice

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Copyright (c) 2026 /LiveRamp, All rights reserved.
 * Created by Rajendhiran Easu on March 16, 2026.
 * Description: 
 */
val listOfUsers: List<String> = listOf("Vetri", "Ameer", "Velraj", "Sana")
val listOfFollowers: List<String> =
    listOf("Anbu", "Rajen", "Senthil", "Guna", "Velu", "Thambi", "Chandra", "Padma", "Pazhani")

class UserViewModel @Inject constructor() : ViewModel() {
    /*private val _userState = MutableStateFlow(User())
    val userState: StateFlow<User> = _userState.asStateFlow()*/

    //compose runtime mutableStateOf and state
    private val _userState1 = mutableStateOf(User())
    val userState1: State<User> = _userState1

    fun getData() {
        viewModelScope.launch {
            delay(2000)
            val username = listOfUsers.random()
            val followers = listOfFollowers.shuffled().take(5).toMutableList()
            //val user = User(name = username, followersList = followers)
            /* _userState.update {
                 it.copy(name = username, followersList = followers)
             }*/
            _userState1.value = User(name = username, followersList = followers)
        }
    }

    fun updateTheFollowers() {
        viewModelScope.launch {
            delay(1000)
            _userState1.value = _userState1.value.copy(followersList = mutableListOf("QA1", "QA2"))
        }
    }

    /**
     * VIOLATION: Mutates in place. Same User reference in State → no recomposition.
     * UI won't update even though data changed. Requires User.followersList to be MutableList.
     */
    fun updateTheFollowersWrongly() {
        viewModelScope.launch {
            delay(1000)
            _userState1.value.followersList.addAll(listOf("Teste1", "teste2"))
        }
    }
}
