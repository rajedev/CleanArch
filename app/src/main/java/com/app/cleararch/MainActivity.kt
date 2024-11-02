package com.app.cleararch

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.cleararch.ui.theme.ClearArchTheme
import com.app.presentation.feed_data.FeedViewModel
import com.app.presentation.games.GamePowerViewModel
import com.app.presentation.games.GameViewModel
import com.app.presentation.pokemon.PokemonViewModel
import com.app.presentation.pokemon_details.PokemonDetailViewModel
import com.app.presentation.quotes.QuotesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val feedViewModel: FeedViewModel by viewModels()
            val pokemonListViewModel: PokemonViewModel by viewModels()
            val pokemonDetailsViewModel: PokemonDetailViewModel by viewModels()
            val quotesViewModel by viewModels<QuotesViewModel>()
            val gameViewModel by viewModels<GameViewModel>()
            val gamePowerViewModel by viewModels<GamePowerViewModel>()
            ClearArchTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Test")
                        }, colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }) { innerPadding ->
                    /* Greeting(
                         feedViewModel,
                         pokemonListViewModel,
                         pokemonDetailsViewModel,
                         gameViewModel,
                         quotesViewModel,
                         gamePowerViewModel,
                         modifier = Modifier.padding(innerPadding),
                     )*/
                    MainScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(
    feedViewModel: FeedViewModel,
    pokemonListViewModel: PokemonViewModel,
    pokemonDetailsViewModel: PokemonDetailViewModel,
    gameViewModel: GameViewModel,
    quotesViewModel: QuotesViewModel,
    gamePowerViewModel: GamePowerViewModel,
    modifier: Modifier = Modifier
) {
    quotesViewModel.getQuotes()
    feedViewModel.createPost()
    feedViewModel.getPosts()
    feedViewModel.getComments()
    pokemonListViewModel.getPokemonList()
    pokemonDetailsViewModel.getPokemonDetails()
    gameViewModel.getGameListData()
    gamePowerViewModel.getGamePowerList()
    Column(
        modifier = modifier, horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Hello ",
            fontSize = TextUnit(40f, TextUnitType.Sp),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp),
        )
    }
}

@Composable
fun MainScreen(modifier: Modifier) {
    Surface(modifier = modifier) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            // Column {
            // for (i in 1..5) {
            // Text(text = "Hello World")
            // }
            // }
            //Column (modifier = Modifier.padding(start = 10.dp)){
            // for (i in 1..5) {
            ShowTexts {
                Column {
                    Text(text = "Hi Welcome", maxLines = 3, overflow = TextOverflow.Ellipsis)
                    Text(text = "Good to Know", maxLines = 3, overflow = TextOverflow.Ellipsis)
                    SelectionContainer {
                        Text(text = "Welcome to Jetpack Compose")
                    }
                    //VerifyVotingRights(::checkVotingRights)
                    //CheckInputFields()
                    //ShowButton()
                    val ctx = LocalContext.current
                    ShowListData(data = dataInfo, action = {
                        checkVotingRights(it).second.let { msg ->
                            Toast.makeText(
                                ctx,
                                msg,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                }
            }
        }
    }
}

data class ListData(val name: String, val yob: Int)

val dataInfo = mutableListOf(
    ListData("John", 1990),
    ListData(
        "Doe dfsdf dsf sdf dfds fds dfds fdfd  dfsd sdfdsf ds dfds d fdsfdDoe dfsdf dsf sdf dfds fds dfds fdfd  dfsd sdfdsf ds dfds d fdsfdDoe dfsdf dsf sdf dfds fds dfds fdfd  dfsd sdfdsf ds dfds d fdsfdDoe dfsdf dsf sdf dfds fds dfds fdfd  dfsd sdfdsf ds dfds d fdsfd",
        1995
    ),
    ListData("Jane", 2000),
    ListData("Aadrine", 2015)
)

@Composable
fun ShowListData(data: List<ListData>, action: (Int) -> Unit) {
    LazyColumn {
        items(data) { item ->
            ShowData(item) { action(item.yob) }
        }
    }
}

@Composable
fun ShowData(data: ListData, action: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height(54.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = data.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .width(200.dp)
                .weight(1f)
                .padding(end = 10.dp),
            style = TextStyle(
                fontSize = 15.sp, fontWeight = FontWeight.SemiBold, fontStyle = FontStyle.Normal,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Button(
            onClick = action,
            modifier = Modifier.width(150.dp)
        ) {
            Text(
                text = "Check Vote rights",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 12.sp, color = Color.White)
            )
        }
    }
    HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.primary)
}


@Composable
fun ShowButton() {
    Log.d("VCompose", "BtnVerify0")
    var increment by remember {
        mutableIntStateOf(0)
    }
    Log.d("VCompose", "BtnVerify4")
    ShowButtonUI(count = increment) {
        increment++
        Log.d("VCompose", "BtnVerify5")
    }
    Log.d("VCompose", "BtnVerify6")
}

@Composable
fun ShowButtonUI(count: Int, action: () -> Unit) {
    Log.d("VCompose", "BtnVerify1")
    Column {
        Log.d("VCompose", "BtnVerify2")
        Button(onClick = action) {
            Log.d("VCompose", "BtnVerify3")
            Text(text = "Click Me $count")
        }
    }
    Row {
        Text(text = "Tester")
    }
}

@Composable
fun InputContent(input: String, onValueChange: (String) -> Unit) {
    Column {
        Log.d("VCompose", "CVerify3")
        TextField(value = input, onValueChange = onValueChange)
        Log.d("VCompose", "CVerify4")
    }
    Log.d("VCompose", "CVerify5")
}

@Composable
fun CheckInputFields() {
    var inputValue by remember {
        mutableStateOf("")
    }
    Log.d("VCompose", "CVerify1")
    Column {
        Button(onClick = {}) {
            Log.d("VCompose", "CVerifyBtn")
            Text(text = "Clicked $inputValue times")
        }
        InputContent(input = inputValue) {
            inputValue = it
            Log.d("VCompose", "CVerify2")
        }
        /*TextField(value = inputValue, onValueChange = {
            Log.d("VCompose","CVerify2")
            inputValue = it
        })*/
        /*Button(onClick = {inputValue++}) {
            Log.d("VCompose","CVerify2")
            Text(text = "Clicked $inputValue times")
        }*/
    }
}

@Composable
fun VerifyVotingRights(clickAction: (String) -> Pair<Boolean, String>) {
    var input by remember {
        mutableStateOf("")
    }
    Log.d("VCompose", "Verify1")
    val errorText = remember { mutableStateOf(TextFieldValue("")) }
    Log.d("VCompose", "Verify2")
    val ctx = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Log.d("VCompose", "Verify3")
    Column {
        Log.d("VCompose", "Verify4")
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            label = { Text("Enter YOB") },
            value = input,
            onValueChange = {
                input = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            supportingText = {
                Text(
                    text = errorText.value.text,
                    color = MaterialTheme.colorScheme.error,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        )
        Log.d("VCompose", "Verify5")
        Button(onClick = {
            Log.d("VCompose", "Verify6")
            keyboardController?.hide()
            errorText.value = TextFieldValue("")
            val result = clickAction(input)
            if (result.first) {
                Toast.makeText(
                    ctx,
                    result.second,
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("VCompose", "Verify7")
            } else {
                errorText.value = TextFieldValue(result.second)
                Log.d("VCompose", "Verify8")
            }

            /*try {
                val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                val userInput = input.value.toInt()
                Toast.makeText(
                    ctx,
                    "You are ${if ((currentYear - userInput) >= 18) " Eligible " else " not Eligible"} to cast your vote",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    ctx, "Please enter a valid year", Toast.LENGTH_SHORT
                ).show()
            }*/
        }) {
            Text("Submit")
        }
    }
}

fun checkVotingRights(inputString: Int): Pair<Boolean, String> = try {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    Pair(
        true,
        "You are ${if ((currentYear - inputString) >= 18) " Eligible " else " not Eligible"} to cast your vote"
    )
} catch (e: Exception) {
    Pair(false, "Please enter a valid year")
}

@Composable
fun ShowTexts(content: @Composable () -> Unit) {
    Row(modifier = Modifier.padding(start = 10.dp)) {
        content()
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DefaultPreview() {
    ClearArchTheme {
        MainScreen(Modifier.padding(10.dp))
    }
}