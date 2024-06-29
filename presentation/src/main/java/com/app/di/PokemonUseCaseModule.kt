package com.app.di

import com.app.domains.repository.pokemon.PokemonDetailRepository
import com.app.domains.repository.pokemon.PokemonRepository
import com.app.domains.usecase.pokemon.GetPokemonDetailUseCase
import com.app.domains.usecase.pokemon.GetPokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * Provides Pokemon Usecase dependency
 */
@InstallIn(SingletonComponent::class)
@Module
class PokemonUseCaseModule {

    @Provides
    fun providesPokemonListUseCase(repository: PokemonRepository) = GetPokemonListUseCase(repository)

    @Provides
    fun providesPokemonDetailsUseCase(repository: PokemonDetailRepository) =
        GetPokemonDetailUseCase(repository)

}
