package com.app.cleararch.practice

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

val listOfNames: List<String> = listOf("Alice", "Bob", "Charlie", "Diana")
val listOfBios: List<String> = listOf(
    "Android developer & coffee lover",
    "Open source enthusiast",
    "Building cool apps one commit at a time",
    "Clean architecture advocate"
)
val listOfPosts: List<String> = listOf(
    "My first post", "Learning Jetpack Compose", "Hilt DI deep dive",
    "StateFlow vs SharedFlow", "Unit testing ViewModels", "Coroutines best practices"
)

class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _profileState = mutableStateOf(Profile())
    val profileState: State<Profile> = _profileState

    fun loadProfile() {
        viewModelScope.launch {
            delay(2000)
            val name = listOfNames.random()
            val bio = listOfBios.random()
            val posts = listOfPosts.shuffled().take(3).toMutableList()
            _profileState.value = Profile(name = name, bio = bio, posts = posts)
        }
    }

    fun updateBio() {
        viewModelScope.launch {
            delay(1000)
            _profileState.value = _profileState.value.copy(bio = listOfBios.random())
        }
    }

    /**
     * VIOLATION: Mutates in place. Same Profile reference in State → no recomposition.
     * UI won't update even though data changed. Requires Profile.posts to be MutableList.
     */
    fun updatePostsWrongly() {
        viewModelScope.launch {
            delay(1000)
            _profileState.value.posts.add("New post (wrong - mutates in place)")
        }
    }
}