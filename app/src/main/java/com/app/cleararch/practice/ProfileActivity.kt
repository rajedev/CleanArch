package com.app.cleararch.practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.cleararch.ui.theme.CleanArchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfilePage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfilePage(modifier: Modifier = Modifier) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val profileObj = profileViewModel.profileState.value
    val isClicked = remember { mutableStateOf(false) }

    val loadProfile = remember(profileViewModel) { { profileViewModel.loadProfile() } }
    val updateBio = remember(profileViewModel) { { profileViewModel.updateBio() } }
    val updatePostsWrong = remember(profileViewModel) { { profileViewModel.updatePostsWrongly() } }

    Column(modifier = modifier) {
        ProfileScreen(
            profileObj = profileObj,
            isClickedStatus = isClicked.value,
            loadProfile = loadProfile,
            simpleClick = { isClicked.value = !isClicked.value },
            updateBio = updateBio,
            updatePostsWrong = updatePostsWrong
        )
    }
}

@Composable
fun ProfileScreen(
    profileObj: Profile,
    isClickedStatus: Boolean,
    loadProfile: () -> Unit = {},
    simpleClick: () -> Unit = {},
    updateBio: () -> Unit = {},
    updatePostsWrong: () -> Unit = {},
) {
    Button(onClick = simpleClick) {
        Text("Click Status: $isClickedStatus")
    }
    Button(onClick = loadProfile) {
        Text("Load Profile")
    }
    Button(onClick = updateBio) {
        Text("Update Bio (correct - uses copy)")
    }
    Button(onClick = updatePostsWrong) {
        Text("Update Posts (wrong - mutates in place)")
    }
    Text(text = "Name: ${profileObj.name}")
    Text(text = "Bio: ${profileObj.bio}")
    Text(text = "Posts (${profileObj.posts.size}):")
    LazyColumn(modifier = Modifier.padding(5.dp)) {
        items(profileObj.posts, key = { it }) {
            Text(text = it)
        }
    }
}

/**
 * For Testing Purpose we made posts as MutableList to show the violation of mutating in place.
 * In real world, we should avoid this and make it immutable list.
 * VIOLATION: Mutates in place. Same Profile reference in State → no recomposition.
 * UI won't update even though data changed. Requires Profile.posts to be MutableList.
 */
@Immutable
data class Profile(val name: String = "", val bio: String = "", val posts: MutableList<String> = mutableListOf())

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    CleanArchTheme {
        ProfilePage()
    }
}
