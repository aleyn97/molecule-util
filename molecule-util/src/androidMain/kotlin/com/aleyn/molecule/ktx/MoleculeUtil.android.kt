package com.aleyn.molecule.ktx

import androidx.compose.ui.platform.AndroidUiDispatcher
import kotlin.coroutines.CoroutineContext

internal actual fun providePlatformDispatcher(): CoroutineContext = AndroidUiDispatcher.Main