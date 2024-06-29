package com.app.presentation.feed_data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.common.utils.UiEvents
import com.app.domains.model.feed.PostRequest
import com.app.domains.usecase.feed.FeedCommentsUseCase
import com.app.domains.usecase.feed.FeedPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Wish
 * Created by Rajendhiran Easu on 02/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This is to handle the Feed related use cases
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val postUseCase: FeedPostUseCase,
    private val commentsUseCase: FeedCommentsUseCase
) : ViewModel() {

    fun getPosts() {
        viewModelScope.launch {
            postUseCase()
                .onEach {
                    when (it) {
                        is UiEvents.Loading -> {
                            Log.d("FEED", "Get Post Loading")
                        }

                        is UiEvents.Success -> {
                            Log.d("FEED", "Get Post Success ${it.data?.get(0)?.body ?: "No Data"}")
                        }

                        is UiEvents.Error -> {
                            Log.d("FEED", "Get Post Error ${it.message}")
                        }
                    }
                }.launchIn(this)
        }
    }

    fun createPost() {
        viewModelScope.launch {
            postUseCase(PostRequest(body = "test", id = 123, title = "test1", userId = 1))
                .onEach {
                    when (it) {
                        is UiEvents.Loading -> {
                            Log.d("FEED", "Create Post Loading")
                        }

                        is UiEvents.Success -> {
                            Log.d("FEED", "Create Post Success ${it.data?.body ?: ""}")
                        }

                        is UiEvents.Error -> {
                            Log.d("FEED", "Create Post Error ${it.message}")
                        }
                    }
                }.launchIn(this)
        }
    }

    fun getComments() {
        viewModelScope.launch {
            commentsUseCase("1")
                .collectLatest {
                    when (it) {
                        is UiEvents.Loading -> {
                            Log.d("FEED", "Get Comments Loading")
                        }

                        is UiEvents.Success -> {
                            Log.d(
                                "FEED",
                                "Get Comments Success ${it.data?.get(0)?.body ?: "No Data"}"
                            )
                        }

                        is UiEvents.Error -> {
                            Log.d("FEED", "Get Comments Error ${it.message}")
                        }
                    }
                }
        }
    }
}
