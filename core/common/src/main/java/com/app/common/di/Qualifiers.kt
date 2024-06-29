package com.app.common.di

import javax.inject.Qualifier

/**
 * Wish
 * Created by Rajendhiran Easu on 12/06/24.
 * Copyright (c) 2021 ContextLogic. All rights reserved.
 *
 * This class will have the custom qualifiers
 */

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class RetrofitInstance

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class KtoRInstance
