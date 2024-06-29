package com.app.cleararch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.app.cleararch.ui.theme.ClearArchTheme
import com.app.presentation.feed_data.FeedViewModel
import com.app.presentation.games.GameViewModel
import com.app.presentation.pokemon.PokemonViewModel
import com.app.presentation.pokemon_details.PokemonDetailViewModel
import com.app.presentation.quotes.QuotesViewModel
import dagger.hilt.android.AndroidEntryPoint

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
            ClearArchTheme {
                Scaffold(
                    topBar =
                    {
                        TopAppBar(
                            title = {
                                Text(text = "Test")
                            }, colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }) { innerPadding ->
                    Greeting(
                        feedViewModel,
                        pokemonListViewModel,
                        pokemonDetailsViewModel,
                        gameViewModel,
                        quotesViewModel,
                        modifier = Modifier.padding(innerPadding),
                    )
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
    modifier: Modifier = Modifier
) {
    quotesViewModel.getQuotes()
    feedViewModel.createPost()
    feedViewModel.getPosts()
    feedViewModel.getComments()
    pokemonListViewModel.getPokemonList()
    pokemonDetailsViewModel.getPokemonDetails()
    gameViewModel.getGameListData()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
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


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClearArchTheme {
        Greeting(
            "Android",
            feedViewModel = feedViewModel,
            pokemonListViewModel = pokemonListViewModel,
            pokemonDetailsViewModel = pokemonDetailsViewModel
        )
    }
}*/
