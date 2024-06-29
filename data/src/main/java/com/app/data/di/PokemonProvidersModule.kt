package com.app.data.di

import com.app.data.provider.PokemonProvider
import com.app.data.repositoryImpl.pokemon.PokemonDetailsImpl
import com.app.data.repositoryImpl.pokemon.PokemonListImpl
import com.app.domains.repository.pokemon.PokemonDetailRepository
import com.app.domains.repository.pokemon.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

/**
 * Wish
 * Created by Rajendhiran Easu on 01/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This provides the data provider
 */
@InstallIn(SingletonComponent::class)
@Module
class PokemonProvidersModule {

    @Provides
    fun providesPokemon(httpClient: HttpClient) = PokemonProvider(httpClient)

    @Provides
    fun providesPokemonListRepo(provider: PokemonProvider): PokemonRepository =
        PokemonListImpl(provider)

    @Provides
    fun providesPokemonDetailsRepo(provider: PokemonProvider): PokemonDetailRepository =
        PokemonDetailsImpl(provider)

}
