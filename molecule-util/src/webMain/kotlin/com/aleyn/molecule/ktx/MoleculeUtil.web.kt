package com.aleyn.molecule.ktx

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal actual fun providePlatformDispatcher(): CoroutineContext = Dispatchers.Main