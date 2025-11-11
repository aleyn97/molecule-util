package com.aleyn.molecule.ktx

import app.cash.molecule.DisplayLinkClock
import kotlinx.coroutines.Dispatchers

internal actual fun providePlatformDispatcher(): kotlin.coroutines.CoroutineContext =
    DisplayLinkClock + Dispatchers.Main