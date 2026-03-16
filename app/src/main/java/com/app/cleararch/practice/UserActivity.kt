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
class UserActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // it is from activity not from compose, so we can use viewModels()
            // to get the instance of viewmodel
            //val userViewModel: UserViewModel by viewModels()
            CleanArchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserProfile(
                        modifier = Modifier.padding(innerPadding)//, userViewModel = userViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun UserProfile(modifier: Modifier = Modifier) {//}, userViewModel: UserViewModel) {
    val userViewModel: UserViewModel = hiltViewModel()
    //val userObj = userViewModel.userState.collectAsState().value
    val userObj = userViewModel.userState1.value
    val isClicked = remember {
        mutableStateOf(false)
    }

    val refresh = remember(userViewModel) { { userViewModel.getData() } }
    val updateTheFollowers = remember(userViewModel) { { userViewModel.updateTheFollowers() } }
    val updateTheFollowersWrong =
        remember(userViewModel) { { userViewModel.updateTheFollowersWrongly() } }

    Column(
        modifier = modifier
    ) {
        UserProfileScreen(
            userObj = userObj,
            isClickedStatus = isClicked.value,
            refresh = refresh,
            simpleClick = {
                isClicked.value = !isClicked.value
            },
            updateTheFollowers = updateTheFollowers,
            updateTheFollowersWrong = updateTheFollowersWrong
        )
    }
}

@Composable
fun UserProfileScreen(
    userObj: User,
    isClickedStatus: Boolean,
    refresh: () -> Unit = {},
    simpleClick: () -> Unit = {},
    updateTheFollowers: () -> Unit = {},
    updateTheFollowersWrong: () -> Unit = {},
) {
    Button(onClick = simpleClick) {
        Text("Click Status: $isClickedStatus")
    }
    Button(onClick = refresh) {
        Text("Refresh Data")
    }
    Button(onClick = updateTheFollowers) {
        Text("Update followers (correct - uses copy)")
    }
    Button(onClick = updateTheFollowersWrong) {
        Text("Update followers (wrong - mutates in place)")
    }
    Text(text = "${userObj.name} has ${userObj.followersList.size} followers")
    LazyColumn(modifier = Modifier.padding(5.dp)) {
        items(userObj.followersList, key = { it }) {
            Text(text = it)
        }
    }
}

/**
 * For Testing Purpose we made followersList as MutableList to show the violation of mutating in place.
 * In real world, we should avoid this and make it immutable list.
 * VIOLATION: Mutates in place. Same User reference in State → no recomposition.
 * UI won't update even though data changed. Requires User.followersList to be MutableList.
 */
@Immutable
data class User(val name: String = "", val followersList: MutableList<String> = mutableListOf())

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CleanArchTheme {
        // UserProfile(userViewModel = hiltViewModel())
        UserProfile()
    }
}
