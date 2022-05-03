package com.soundscape.infrastructure

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

// We need to know which activity we're in
fun Context.findActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}